package objektwerks

import ox.*

import scala.io.{Codec, Source}
import scala.util.Using

@main
def runApp: Unit =
  val (joke1, joke2, joke3) = par( getJoke(), getJoke(), getJoke() )
  println(joke1)
  println(joke2)
  println(joke3)

def getJoke(): String =
  Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => s"* ${parseJson( source.mkString )}"
  }.getOrElse("Chuck Norris is taking a power nap! Come back in a few nanoseconds!")

def parseJson(json: String): String = ujson.read(json)("value").str