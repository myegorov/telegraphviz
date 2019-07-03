val scalaVer = "2.12.8"

lazy val commonSettings = Seq(
  scalaVersion := scalaVer,
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xlint"
  ),
  libraryDependencies ++= Seq(
    "org.scalatest"   %% "scalatest"    % "3.0.5"   % "test",
    "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
    "com.github.scopt" %% "scopt" % "4.0.0-RC2"
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(Seq(
    mainClass in (Compile, packageBin) := Some("cli.Main"),
    mainClass in (Compile, run) := Some("cli.Main"),
    mainClass in assembly := Some("cli.Main")
  ))
