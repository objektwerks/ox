package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork"):
    val lineCount = supervised {
      val alines = fork( countLines(aFile) )
      val blines = fork( countLines(bFile) )
      alines.join() + blines.join()
    }
    lineCount shouldBe expectedLineCount