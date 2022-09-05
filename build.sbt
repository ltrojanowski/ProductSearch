ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ProductSearch",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalameta" %% "munit" % "0.7.28" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
