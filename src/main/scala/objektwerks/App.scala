package objektwerks

import ox.*

import scala.io.{Codec, Source}
import scala.util.Using

@main
def runApp: Unit = par( getJoke(), getJoke(), getJoke() ).toList.foreach(println)

def getJoke(): String =
  Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => s"* ${parseJson( source.mkString )}"
  }.getOrElse("Chuck Norris is taking a power nap! Come back in a few nanoseconds!")

def parseJson(json: String): String = ujson.read(json)("value").str