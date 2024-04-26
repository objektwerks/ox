package objektwerks

import ox.*

@main
def runSupervisedApp: Unit =
  val jokes = supervised:
    val joke1 = forkUser( getJoke() )
    val joke2 = forkUser( getJoke() )
    val joke3 = forkUser( getJoke() )
    List( joke1.join(), joke2.join(), joke3.join() )
  jokes.foreach(println)