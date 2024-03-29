package objektwerks

import java.util.UUID

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class ScopedTest extends AnyFunSuite with Matchers:
  test("scoped > fork"):
    val totalFileLineCount = scoped:
      val aCountFileLinesFork = fork( countFileLines(aFile) )
      val bCountFileLinesFork = fork( countFileLines(bFile) )
      aCountFileLinesFork.join() + bCountFileLinesFork.join()
    totalFileLineCount shouldBe expectedFileLineCount

  test("scoped > fork > scoped value"):
    val license = ForkLocal("")
    val uuid = UUID.randomUUID.toString
    val count = scoped {
      fork {
        license.scopedWhere(uuid) {
          if license.get().nonEmpty then 1 else -1
        }
      }
    }.join()
    count shouldBe 1