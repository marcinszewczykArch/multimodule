ThisBuild / scalaVersion := "3.3.5"

// Wspólne zależności (opcjonalnie)
val AkkaHttpVersion = "10.5.2"
val AkkaActorVersion = "2.8.0"

lazy val commonDependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaActorVersion
)

// Projekt główny
lazy val root = (project in file("."))
  .aggregate(utils, loadBalancer, myApp, performanceTests)
  .settings(
    name := "multi-module-project",
    version := "1.0"
  )

// Moduł utils (wspólna logika)
lazy val utils = (project in file("modules/utils"))
  .settings(
    name := "utils",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.10.0",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    )
  )

// Moduł load-balancer
lazy val loadBalancer = (project in file("modules/load-balancer"))
  .dependsOn(utils)
  .settings(
    name := "load-balancer",
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    )
  )

// Moduł my-app (API)
lazy val myApp = (project in file("modules/my-app"))
  .dependsOn(utils)
  .settings(
    name := "my-app",
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    )
  )

// Moduł performance-tests (Gatling)
lazy val performanceTests = (project in file("modules/performance-tests"))
  .settings(
    name := "performance-tests",
    libraryDependencies ++= Seq(
      "io.gatling" % "gatling-test-framework" % "3.9.5" % Test,
      "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.9.5" % Test
    )
  )
