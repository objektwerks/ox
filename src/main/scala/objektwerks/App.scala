package objektwerks

import ox.*

import scala.io.{Codec, Source}
import scala.util.Using

@main
def runApp: Unit =
  val (joke1, joke2) = par( getJoke(), getJoke() )
  println(joke1)
  println(joke2)

def getJoke(): String =
  Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => parseJson( source.mkString )
  }.get

def parseJson(json: String): String = ujson.read(json)("value").str