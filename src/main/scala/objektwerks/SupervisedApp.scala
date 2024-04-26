package objektwerks

import ox.*

@main
def runSupervisedApp: Unit =
  supervised:
    List(
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join()
    )
  .foreach(println)