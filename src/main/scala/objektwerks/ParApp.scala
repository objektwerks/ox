package objektwerks

import ox.*

/**
  * See: https://ox.softwaremill.com/latest/high-level-concurrency/par.html
  */
object ParApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    supervised:
      par(
        getJoke(),
        getJoke(),
        getJoke()
      )
      .toList
      .foreach(println)

    ExitCode.Success