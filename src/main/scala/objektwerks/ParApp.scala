package objektwerks

import ox.*

@main
def runParApp: Unit =
  par(
    getJoke(),
    getJoke(),
    getJoke()
  )
  .toList
  .foreach(println)