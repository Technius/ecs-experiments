name := """ecs-experiments"""

version := "1.0.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xlint",
  "-Xfatal-warnings"
)
