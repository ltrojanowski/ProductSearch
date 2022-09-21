ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / crossScalaVersions := Seq("2.12.17", "2.13.8")

lazy val root = (project in file("."))
  .settings(
    name := "ProductSearch",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalameta" %% "munit" % "0.7.28" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
  .dependsOn(core)

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalameta" %% "munit" % "0.7.28" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
