name := "ox"
organization := "objektwerks"
version := "0.8-SNAPSHOT"
scalaVersion := "3.5.0-RC1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.1.1",
    "com.lihaoyi" %% "ujson" % "3.3.0",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
