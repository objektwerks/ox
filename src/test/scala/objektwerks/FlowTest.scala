package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.flow.Flow

final class FlowTest  extends AnyFunSuite with Matchers:
  test("flow"):
    val total = Flow
      .fromValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      .map(i => i * i)
      .mapPar(2)(i => i - 1)
      .runToList()
      .sum
    total shouldBe 375