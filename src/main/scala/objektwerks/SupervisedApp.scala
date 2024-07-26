package objektwerks

import ox.*

/**
  * See: https://ox.softwaremill.com/latest/structured-concurrency/fork-join.html
  */
object SupervisedApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    supervised:
      List(
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join()
      )
      .foreach(println)
    ExitCode.Success
