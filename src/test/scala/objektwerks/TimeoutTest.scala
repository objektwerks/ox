package objektwerks

import scala.concurrent.duration.*

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import objektwerks.Fibonacci.fibonacci

class TimeoutTest extends AnyFunSuite with Matchers:
  test("timeout"):
    timeout(10.micros)(fibonacci(96))