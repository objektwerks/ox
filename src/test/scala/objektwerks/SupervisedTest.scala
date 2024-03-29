package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork"):
    val lineCount = supervised {
      val alines: Fork[Int] = fork( countLines("./data/data.a.csv") )
      val blines: Fork[Int] = fork( countLines("./data/data.b.csv") )
      alines.join() + blines.join()
    }
    lineCount shouldBe expectedLineCount