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
    "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
  )
)

// Root subproject: will not publish JARs, only aggregate other subprojects.
// So e.g. `sbt test` will run tests in the aggregated subprojects.
lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(subproject) // add subprojects here

// sbt subproject/run
lazy val subproject = (project in file("subproject"))
  .settings(commonSettings)
  .settings(Seq(
    mainClass in (Compile, packageBin) := Some("subproject.Main"),
    mainClass in (Compile, run) := Some("subproject.Main"),
    libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.0-RC2"
  ))
