name := "my-app"
scalaVersion := "3.3.5"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.2",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)
