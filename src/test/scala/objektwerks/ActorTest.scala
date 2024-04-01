package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import java.util.concurrent.atomic.AtomicInteger


class StatefulActor:
  val counter = AtomicInteger(0)
  def increment(delta: Int): Int = counter.addAndGet(delta)

class ActorTest extends AnyFunSuite with Matchers:
  test("actor"):
    true