enablePlugins(JmhPlugin)

name := "ox"
organization := "objektwerks"
version := "6.0.0"
scalaVersion := "3.8.1"
libraryDependencies ++= {
  val oxVersion = "1.0.2"
  Seq(
    "com.softwaremill.ox" %% "core" % oxVersion,
    "com.softwaremill.ox" %% "otel-context" % oxVersion,
    "org.scalikejdbc" %% "scalikejdbc" % "4.3.5",
    "com.zaxxer" % "HikariCP" % "7.0.2" exclude("org.slf4j", "slf4j-api"),
    "org.postgresql" % "postgresql" % "42.7.8",
    "org.jodd" % "jodd-mail" % "7.0.1",
    "com.lihaoyi" %% "ujson" % "4.4.2",
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.25",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
