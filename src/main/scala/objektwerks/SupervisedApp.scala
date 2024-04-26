package objektwerks

import ox.*

@main
def runSupervisedApp: Unit =
  val jokes = supervised:
    List(
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join(),
      forkUser( getJoke() ).join()
    )
  jokes.foreach(println)