name := "ox"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.1-RC1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.ox" %% "core" % "0.0.20",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)