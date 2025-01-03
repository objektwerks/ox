package objektwerks

import com.typesafe.config.ConfigFactory

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Fork, Measurement, Mode, OutputTimeUnit, Scope, State, Warmup}

import ox.*

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
class Performance():
  val store = Store( ConfigFactory.load("store.conf") )
  var todo = Todo(task = "")

  @Benchmark
  def addTodo(): Todo =
    supervised:
      todo = Todo(task = UUID.randomUUID.toString)
      todo = todo.copy(id = store.addTodo(todo))
      todo

  @Benchmark
  def updateTodo(): Int =
    supervised:
      todo = todo.copy(task = UUID.randomUUID.toString)
      store.updateTodo(todo)

  @Benchmark
  def listTodos(): Seq[Todo] =
    supervised:
      store.listTodos()