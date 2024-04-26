package objektwerks

import ox.*

@main
def runApp: Unit = par( getJoke(), getJoke(), getJoke() ).toList.foreach(println)