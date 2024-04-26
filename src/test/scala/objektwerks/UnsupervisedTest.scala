package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class UnsupervisedTest extends AnyFunSuite with Matchers:
  test("unsupervised > fork cancellable"):
    val totalFileLineCount = unsupervised:
      val aCountFileLinesFork = forkCancellable( countFileLines(aFile) )
      val bCountFileLinesFork = forkCancellable( countFileLines(bFile) )
      aCountFileLinesFork.join() + bCountFileLinesFork.join()
    totalFileLineCount shouldBe expectedFileLineCount