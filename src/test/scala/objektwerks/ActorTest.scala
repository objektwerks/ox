package objektwerks

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.channels.Actor

class Counter:
  private val count = AtomicInteger(0)
  def increment(number: Int): Int = count.addAndGet(number)
  def value: Int = count.get()

class ActorTest extends AnyFunSuite with Matchers:
  test("actor > ask"):
    val count = supervised:
      val actor = Actor.create(Counter())
      actor.ask(_.increment(1))
      actor.ask(_.increment(2))
      actor.ask(_.increment(3))
      actor.ask(_.value)
    count shouldBe 6