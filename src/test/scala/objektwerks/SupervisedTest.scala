package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork"):
    val lineCount = supervised {
      val aLines = fork( countFileLines(aFile) )
      val bLines = fork( countFileLines(bFile) )
      aLines.join() + bLines.join()
    }
    lineCount shouldBe expectedLineCount