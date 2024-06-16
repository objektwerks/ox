package objektwerks

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite

final class StoreTest extends AnyFunSuite:
  val store = Store( ConfigFactory.load("test.conf") )

  test("store"):
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