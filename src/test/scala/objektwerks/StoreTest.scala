package objektwerks

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.IO.globalForTesting.given

import scala.sys.process.Process

final class StoreTest extends AnyFunSuite with Matchers:
  val exitCode = Process("psql -d todo -f ddl.sql").run().exitValue()
  exitCode shouldBe 0

  val config = StoreConfig( ConfigFactory.load("test.conf") )
  val store = Store(config)

  test("store"):
    supervised:
      var todo = Todo(task = "wash car")
      val id = store.addTodo(todo)
      todo = todo.copy(id = id)
      println(s"*** Add Todo - $todo")
      assert( todo.id > 0 )

      val updatedTodo = todo.copy(task = "wash and dry car")
      val updated = store.updateTodo(updatedTodo)
      println(s"*** Update Todo - $updatedTodo")
      assert( updated > 0 )

      val todos = store.listTodos()
      println(s"*** List Todos = ${todos.toString}")
      assert( todos.nonEmpty )
