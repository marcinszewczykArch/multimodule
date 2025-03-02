ThisBuild / scalaVersion := "3.3.5"

lazy val root = (project in file("."))
  .aggregate(utils, loadBalancer, myApp, performanceTests)
  .settings(
    name := "multi-module-project",
    version := "1.0"
  )

lazy val utils = (project in file("modules/utils"))
lazy val loadBalancer = (project in file("modules/load-balancer"))
  .dependsOn(utils)
lazy val myApp = (project in file("modules/my-app"))
  .dependsOn(utils)
lazy val performanceTests = (project in file("modules/performance-tests"))
