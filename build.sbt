name := "ox"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.0-RC1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.0.16",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
