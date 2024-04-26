package objektwerks

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.channels.Actor

class Counter:
  private val count = AtomicInteger(0)
  def increment(number: Int): Int = count.addAndGet(number)
  def value(): Int = count.get()
  def close(): Unit = count.set(0)

/**
  * See: https://ox.softwaremill.com/latest/channels/actors.html
  */
class ActorTest extends AnyFunSuite with Matchers:
  test("actor > ask"):
    val count = supervised:
      val counter = Actor.create(Counter(), Some( _.close() ))
      counter.ask(_.increment(1))
      counter.ask(_.increment(2))
      counter.ask(_.increment(3))
      counter.ask(_.value())
    count shouldBe 6

  test("actor > tell"):
    val count = supervised:
      val counter = Actor.create(Counter())
      counter.tell(_.increment(1))
      counter.tell(_.increment(2))
      counter.tell(_.increment(3))
      counter.ask(_.value())
    count shouldBe 6