package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import scala.concurrent.duration.DurationInt

import FileLineCount.*

/**
  * See: https://ox.softwaremill.com/latest/high-level-concurrency/timeout.html
  */
final class TimeoutTest extends AnyFunSuite with Matchers:
  test("timeout either"):
    supervised:
      val result = timeoutEither(1.milliseconds, 0) { Right( countFileLines(aFile) ) }
      result.isLeft shouldBe true

  test("timeout option"):
    supervised:
      val result = timeoutOption(100.nano) { countFileLines(aFile) }
      result shouldBe None