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
    val exception = intercept[FileNotFoundException] (
      supervised:
        val countFileLinesFork = forkUser( countFileLines("non.existent.file") )
        countFileLinesFork.join()
    )
    exception shouldBe a[FileNotFoundException]

  test("supervised error > fork user error"):
    val result = supervisedError(EitherMode[Int]):
      forkUserError { Left(10) } 
      Right(()) 
    result shouldBe Left(10)

  test("unsupervised > fork cancellable"):
    val totalFileLineCount = unsupervised:
      val aCountFileLinesFork = forkCancellable( countFileLines(aFile) )
      val bCountFileLinesFork = forkCancellable( countFileLines(bFile) )
      aCountFileLinesFork.join() + bCountFileLinesFork.join()
    totalFileLineCount shouldBe expectedFileLineCount