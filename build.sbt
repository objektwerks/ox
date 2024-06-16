autoCompilerPlugins := true
addCompilerPlugin("com.softwaremill.ox" %% "plugin" % "0.2.1")

enablePlugins(JmhPlugin)

name := "ox"
organization := "objektwerks"
version := "0.9-SNAPSHOT"
scalaVersion := "3.5.0-RC1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.2.1",
    "org.scalikejdbc" %% "scalikejdbc" % "4.3.0",
    "com.zaxxer" % "HikariCP" % "5.1.0" exclude("org.slf4j", "slf4j-api"),
    "org.postgresql" % "postgresql" % "42.7.3",
    "com.lihaoyi" %% "ujson" % "3.3.1",
    "com.typesafe" % "config" % "1.4.3",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)