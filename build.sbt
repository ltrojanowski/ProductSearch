ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.13.8"
ThisBuild / crossScalaVersions := Seq("2.13.8", "2.12.17")
ThisBuild / organization := "com.ltrojanowski"
ThisBuild / licenses := Seq("MIT" -> url("https://github.com/ltrojanowski/product-search/blob/master/LICENSE.md"))
ThisBuild / description := "This library allows you to find the value of a specific type in a nested tuple."

import xerial.sbt.Sonatype._
ThisBuild / sonatypeProjectHosting := Some(GitHubHosting("ltrojanowski", "product-search", "luk.trojanowski@gmail.com"))
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

// publish to the sonatype repository
ThisBuild / publishTo := sonatypePublishToBundle.value

lazy val root = (project in file("."))
  .settings(
    name := "product-search",
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
