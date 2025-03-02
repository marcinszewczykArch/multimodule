import sttp.tapir.*
import cats.effect.{ExitCode, IO, IOApp}
import com.comcast.ip4s.{Host, Port}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import sttp.tapir.server.http4s.{Http4sServerInterpreter, Http4sServerOptions}

object Main extends IOApp:

  override def run(args: List[String]): IO[ExitCode] =

    val serverOptions: Http4sServerOptions[IO] =
      Http4sServerOptions
        .customiseInterceptors[IO]
        .metricsInterceptor(Endpoints.prometheusMetrics.metricsInterceptor())
        .options
    val routes =
      Http4sServerInterpreter[IO](serverOptions).toRoutes(Endpoints.all)

    val instanceName: String =
      Option(System.getenv("INSTANCE_NAME")).getOrElse("default-instance")
    val dockerPort: String = Option(System.getenv("PORT")).getOrElse("8080")
    println(s"instanceName: $instanceName, port: $dockerPort")

    EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString("0.0.0.0").get)
      .withPort(Port.fromInt(8080).get)
      .withHttpApp(Router("/" -> routes).orNotFound)
      .build
      .use: server =>
        for
          _ <- IO.println(
            s"Go to http://localhost:$dockerPort/docs to open SwaggerUI. Press ENTER key to exit."
          )
          _ <- IO.readLine
        yield ()
      .as(ExitCode.Success)
