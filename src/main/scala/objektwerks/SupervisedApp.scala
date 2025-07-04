package objektwerks

import ox.{forkUser, ExitCode, Ox, OxApp, supervised}

/**
  * See: https://ox.softwaremill.com/latest/structured-concurrency/fork-join.html
  */
object SupervisedApp extends OxApp:
  def run(args: Vector[String])(using Ox): ExitCode =
    supervised:
      List(
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join()
      )
      .foreach(println)

    ExitCode.Success