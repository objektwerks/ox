package objektwerks

import ox.*

/**
  * See: https://ox.softwaremill.com/latest/high-level-concurrency/par.html
  */
@main
def runParApp: Unit =
  IO.unsafe:
    par(
      getJoke(),
      getJoke(),
      getJoke()
    )
    .toList
    .foreach(println)