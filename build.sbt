import sbt.Keys._

lazy val akkaVersion = "2.5.2"

lazy val root = (project in file("."))
 .settings(
    name := "s3mock",
    organization := "io.findify",
    scalaVersion := "2.12.2",

    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % "10.0.7",
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
      "com.github.pathikrit" %% "better-files" % "2.17.1",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
      "com.amazonaws" % "aws-java-sdk-s3" % "1.11.149",
      "org.scalatest" %% "scalatest" % "3.0.3" % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "test",
      "org.iq80.leveldb" % "leveldb" % "0.9",
      "com.lightbend.akka" %% "akka-stream-alpakka-s3" % "0.9" % "test"
    )
  )
  .settings(releaseSettings: _*)

lazy val releaseSettings = Seq(
  crossScalaVersions := Seq("2.11.11", "2.12.2"),
  licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
  homepage := Some(url("https://github.com/findify/s3mock")),
  publishMavenStyle := true,

  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },

  pomExtra := (
    <scm>
      <url>git@github.com:findify/s3mock.git</url>
      <connection>scm:git:git@github.com:findify/s3mock.git</connection>
    </scm>
      <developers>
        <developer>
          <id>romangrebennikov</id>
          <name>Roman Grebennikov</name>
          <url>http://www.dfdx.me</url>
        </developer>
      </developers>)
)

parallelExecution in Test := false
