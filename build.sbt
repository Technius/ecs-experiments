name := """ecs-experiments"""

version := "1.0.0"

lazy val sharedSettings = Seq(
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-Xlint",
    "-Xfatal-warnings"
  ),
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % "2.11.7" % "provided"
  )
)

lazy val root = (project in file(".")).aggregate(core, macros)

lazy val core =
  (project in file("core"))
    .settings(sharedSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "2.2.4" % "test",
        "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
      )
    )
    .dependsOn(macros)

lazy val macros =
  (project in file("macros"))
    .settings(sharedSettings: _*)
