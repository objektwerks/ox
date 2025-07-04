package objektwerks

import ox.{ExitCode, Ox, OxApp, par, supervised}

/**
  * See: https://ox.softwaremill.com/latest/high-level-concurrency/par.html
  */
object ParApp extends OxApp:
  def run(args: Vector[String])(using Ox): ExitCode =
    supervised:
      par(
        getJoke(),
        getJoke(),
        getJoke()
      )
      .toList
      .foreach(println)

    ExitCode.Success