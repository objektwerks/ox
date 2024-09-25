package objektwerks

import scala.io.{Codec, Source}
import scala.util.Using

def getJoke(): String =
  Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => s"* ${parseJson( source.mkString )}"
  }.getOrElse("Chuck Norris is taking a power nap! Come back in a few nanoseconds!")

private def parseJson(json: String): String = ujson.read(json)("value").str