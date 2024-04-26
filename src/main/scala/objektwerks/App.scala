package objektwerks

import scala.io.{Codec, Source}
import scala.util.Using

@main
def runApp: Unit =
  val joke = Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => source.mkString
  }.get
  println(joke)