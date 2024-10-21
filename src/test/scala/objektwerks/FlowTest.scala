package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.flow.Flow

final class FlowTest  extends AnyFunSuite with Matchers:
  test("flow"):
    val result = Flow
      .fromValues(1, 2, 3)
      .map(i => i * i)
      .runToList()
    result.sum shouldBe 14