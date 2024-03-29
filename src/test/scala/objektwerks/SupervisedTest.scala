package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork"):
    val totalFileLineCount = supervised:
      val aCountFileLinesFork = fork( countFileLines(aFile) )
      val bCountFileLinesFork = fork( countFileLines(bFile) )
      aCountFileLinesFork.join() + bCountFileLinesFork.join()
    totalFileLineCount shouldBe expectedFileLineCount