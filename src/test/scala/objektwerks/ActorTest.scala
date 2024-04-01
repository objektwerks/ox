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

class ActorTest extends AnyFunSuite with Matchers:
  test("actor > ask"):
    val count = supervised:
      val counter = Actor.create(Counter())
      counter.ask(_.increment(1))
      counter.ask(_.increment(2))
      counter.ask(_.increment(3))
      counter.ask(_.value())
    count shouldBe 6