package objektwerks

import java.io.FileNotFoundException

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class SupervisedTest extends AnyFunSuite with Matchers:
  test("supervised > fork user"):
    val totalFileLineCount = supervised:
      val aCountFileLinesFork = forkUser( countFileLines(aFile) )
      val bCountFileLinesFork = forkUser( countFileLines(bFile) )
      aCountFileLinesFork.join() + bCountFileLinesFork.join()
    totalFileLineCount shouldBe expectedFileLineCount

  test("supervised > fork user > exception"):
    val exception = intercept[FileNotFoundException] ( supervised:
      val countFileLinesFork = forkUser( countFileLines("non.existent.file") )
      countFileLinesFork.join()
    )
    exception shouldBe a[FileNotFoundException]