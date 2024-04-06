package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.retry.{retry, retryEither, RetryPolicy}

import FileLineCount.*

final class RetryTest extends AnyFunSuite with Matchers:
  test("retry"):
    val fileLineCount =  retry( RetryPolicy.immediate(2) )( countFileLines(aFile) )
    fileLineCount shouldBe aFileLineCount

  test("retry either"):
    val fileLineCount =  retryEither( RetryPolicy.immediate(2) )( Right( countFileLines(aFile) ) )
    fileLineCount.getOrElse(0) shouldBe aFileLineCount