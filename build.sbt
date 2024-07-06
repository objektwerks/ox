val oxVersion = "0.2.2"

autoCompilerPlugins := true
addCompilerPlugin("com.softwaremill.ox" %% "plugin" % oxVersion)

enablePlugins(JmhPlugin)

name := "ox"
organization := "objektwerks"
version := "0.10-SNAPSHOT"
scalaVersion := "3.5.0-RC3"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % oxVersion,
    "org.scalikejdbc" %% "scalikejdbc" % "4.3.0",
    "com.zaxxer" % "HikariCP" % "5.1.0" exclude("org.slf4j", "slf4j-api"),
    "org.postgresql" % "postgresql" % "42.7.3",
    "com.lihaoyi" %% "ujson" % "3.3.1",
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.6",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
