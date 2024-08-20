package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object StoreApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    val store = Store( ConfigFactory.load("store.conf") )
    val todo = Todo(task = "Drink Dogfishhead 60 Minute IPA!")

    supervised:
      val add = retryEither( RetryConfig.immediate(2) )( Right( store.addTodo(todo) ) )
      assert( add.isRight )

    supervised:
      val update = retryEither( RetryConfig.immediate(2) )( Right( store.updateTodo(todo.copy(task = "Drink DogfishHead 60 Minute IPA!")) ) )
      assert( update.isRight )

    supervised:
      val list = retryEither( RetryConfig.immediate(2) )( Right( store.listTodos() ) )
      assert( list.isRight )

    ExitCode.Success
