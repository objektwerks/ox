package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import scala.concurrent.duration.DurationInt
import scala.concurrent.TimeoutException

import objektwerks.Fibonacci.fibonacci

final class TimeoutTest extends AnyFunSuite with Matchers:
  test("timeout"):
    val exception = intercept[TimeoutException]( timeout(10.milliseconds)(fibonacci(96)) )
    exception shouldBe a[TimeoutException]