name := "performance-tests"
scalaVersion := "3.3.5"
libraryDependencies ++= Seq(
  "io.gatling" % "gatling-test-framework" % "3.9.5" % Test,
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.9.5" % Test
)
