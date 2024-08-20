package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object StoreApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    val store = Store( StoreConfig( ConfigFactory.load("test.conf") ) )
    val todo = Todo(task = "Drink Dogfishhead 60 Minute IPA!")

    supervised:
      val either = retryEither( RetryConfig.immediate(2) )( Right( store.addTodo(todo) ) )
      assert( either.isRight )

    ExitCode.Success
