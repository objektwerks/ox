enablePlugins(JmhPlugin)

name := "ox"
organization := "objektwerks"
version := "0.17-SNAPSHOT"
scalaVersion := "3.5.1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.4.0",
    "org.scalikejdbc" %% "scalikejdbc" % "4.3.1",
    "com.zaxxer" % "HikariCP" % "5.1.0" exclude("org.slf4j", "slf4j-api"),
    "org.postgresql" % "postgresql" % "42.7.4",
    "org.jodd" % "jodd-mail" % "7.0.1",
    "com.lihaoyi" %% "ujson" % "4.0.2",
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.8",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
