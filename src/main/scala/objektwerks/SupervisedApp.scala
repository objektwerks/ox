package objektwerks

import ox.*

/**
  * See: https://ox.softwaremill.com/latest/structured-concurrency/fork-join.html
  */
@main
def runSupervisedApp: Unit =
  supervised:
    List(
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join()
    )
  .foreach(println)