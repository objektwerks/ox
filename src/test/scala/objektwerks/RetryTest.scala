package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.retry.{retry, retryEither, RetryPolicy}

import FileLineCount.*

final class RetryTest extends AnyFunSuite with Matchers:
  test("retry"):
    val fileLineCount =  retry( countFileLines(aFile) )( RetryPolicy.immediate(2) )
    fileLineCount shouldBe aFileLineCount

  test("retry either"):
    val fileLineCount =  retryEither( Right( countFileLines(aFile) ) )( RetryPolicy.immediate(2) )
    fileLineCount.getOrElse(0) shouldBe aFileLineCount