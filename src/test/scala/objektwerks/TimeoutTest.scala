package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import scala.concurrent.duration.DurationInt

import FileLineCount.*

final class TimeoutTest extends AnyFunSuite with Matchers:
  test("timeout either"):
    val result = timeoutEither(10.milliseconds, 0) { Right( countFileLines(aFile) ) }
    result.isLeft shouldBe true

  test("timeout option"):
    val result = timeoutOption(10.milliseconds) { countFileLines(aFile) }
    result shouldBe None