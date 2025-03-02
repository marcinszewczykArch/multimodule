name := "load-balancer"
scalaVersion := "3.3.5"
libraryDependencies ++= Seq()

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
