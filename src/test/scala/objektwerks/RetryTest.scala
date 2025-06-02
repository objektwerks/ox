package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.{supervised}
import ox.resilience.{retry, retryEither}
import ox.scheduling.Schedule

import FileLineCount.*

/**
  * See: https://ox.softwaremill.com/latest/retries.html
  */
final class RetryTest extends AnyFunSuite with Matchers:
  test("retry"):
    supervised:
      val fileLineCount =  retry( Schedule.immediate )( countFileLines(aFile) )
      fileLineCount shouldBe aFileLineCount

  test("retry either"):
    supervised:
      val fileLineCount =  retryEither( Schedule.immediate )( Right( countFileLines(aFile) ) )
      fileLineCount.getOrElse(0) shouldBe aFileLineCount