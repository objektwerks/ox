enablePlugins(JmhPlugin)

name := "ox"
organization := "objektwerks"
version := "1.0.0"
scalaVersion := "3.7.2-RC1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.6.1",
    "org.scalikejdbc" %% "scalikejdbc" % "4.3.2",
    "com.zaxxer" % "HikariCP" % "6.3.0" exclude("org.slf4j", "slf4j-api"),
    "org.postgresql" % "postgresql" % "42.7.5",
    "org.jodd" % "jodd-mail" % "7.0.1",
    "com.lihaoyi" %% "ujson" % "4.2.1",
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.18",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)