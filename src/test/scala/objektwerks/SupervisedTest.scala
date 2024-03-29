package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork"):
    val lineCount = supervised {
      val aFileLines = fork( countFileLines(aFile) )
      val bFileLines = fork( countFileLines(bFile) )
      aFileLines.join() + bFileLines.join()
    }
    lineCount shouldBe expectedFileLineCount