package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.flow.Flow

final class FlowTest  extends AnyFunSuite with Matchers:
  test("flow"):
    val total = Flow
      .fromValues(1, 2, 3)
      .map(i => i * i)
      .runToList()
      .sum
    total shouldBe 14