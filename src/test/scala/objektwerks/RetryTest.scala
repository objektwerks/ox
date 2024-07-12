package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.IO.globalForTesting.given
import ox.resilience.*

import FileLineCount.*

/**
  * See: https://ox.softwaremill.com/latest/retries.html
  */
final class RetryTest extends AnyFunSuite with Matchers:
  test("retry"):
    val fileLineCount =  retry( RetryConfig.immediate(2) )( countFileLines(aFile) )
    fileLineCount shouldBe aFileLineCount

  test("retry either"):
    val fileLineCount =  retryEither( RetryConfig.immediate(2) )( Right( countFileLines(aFile) ) )
    fileLineCount.getOrElse(0) shouldBe aFileLineCount