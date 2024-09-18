package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.IO.globalForTesting.given

import FileLineCount.*

/**
  * See: https://ox.softwaremill.com/latest/structured-concurrency/fork-join.html
  */
final class UnsupervisedTest extends AnyFunSuite with Matchers:
  test("unsupervised > fork cancellable"):
    unsupervised:
      val aCountFileLinesFork = forkCancellable( countFileLines(aFile) )
      val bCountFileLinesFork = forkCancellable( countFileLines(bFile) )
      val totalFileLineCount = aCountFileLinesFork.join() + bCountFileLinesFork.join()
      totalFileLineCount shouldBe expectedFileLineCount