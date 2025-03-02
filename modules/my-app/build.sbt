name := "my-app"
scalaVersion := "3.3.5"

val tapirVersion = "1.11.13"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
  "org.http4s" %% "http4s-ember-server" % "0.23.30",
  "com.softwaremill.sttp.tapir" %% "tapir-prometheus-metrics" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.16",
  "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % tapirVersion % Test,
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "com.softwaremill.sttp.client3" %% "circe" % "3.10.3" % Test
)

import sbtassembly.MergeStrategy

assembly / assemblyMergeStrategy := {
  case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.concat
  case PathList(ps @ _*) if ps.last endsWith ".conf"       => MergeStrategy.concat
  case PathList(ps @ _*) if ps.last == "schema"            => MergeStrategy.concat
  case PathList(ps @ _*) if ps.last == "module-info.class" => MergeStrategy.discard
  case x                                                   =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}

