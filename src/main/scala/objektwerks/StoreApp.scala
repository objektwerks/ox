package objektwerks

import com.typesafe.config.ConfigFactory

import ox.{ExitCode, Ox, OxApp, supervised}
import ox.resilience.retryEither
import ox.scheduling.Schedule

/**
* See StoreTest for the simplified version, which groups all store ops within a single supervised clause.
*
* This app wraps each store op in an individual io / supervised clause.
*/
object StoreApp extends OxApp:
  def run(args: Vector[String])(using Ox): ExitCode =
    val store = Store( ConfigFactory.load("store.conf") )
    val todo = Todo(task = "Drink Dogfishhead 60 Minute IPA!")

    supervised:
      val add = retryEither( Schedule.immediate )( Right( store.addTodo(todo) ) )
      assert( add.isRight )

    supervised:
      val update = retryEither( Schedule.immediate )( Right( store.updateTodo(todo.copy(task = "Drink DogfishHead 60 Minute IPA!")) ) )
      assert( update.isRight )

    supervised:
      val list = retryEither( Schedule.immediate )( Right( store.listTodos() ) )
      assert( list.isRight )

    ExitCode.Success