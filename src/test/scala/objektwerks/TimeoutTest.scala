package objektwerks

import java.util.concurrent.TimeoutException

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import scala.concurrent.duration.DurationInt

import objektwerks.Fibonacci.fibonacci

final class TimeoutTest extends AnyFunSuite with Matchers:
  test("timeout"):
    val exception = intercept[TimeoutException]( timeout(10.micros)(fibonacci(96)) )
    exception shouldBe a[TimeoutException]