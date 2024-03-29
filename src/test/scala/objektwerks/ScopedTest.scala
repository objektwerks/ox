package objektwerks

import java.util.UUID

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class ScopedTest extends AnyFunSuite with Matchers:
  test("scoped > fork"):
    val lineCount = scoped {
      val aLines = fork( countLines(aFile) )
      val bLines = fork( countLines(bFile) )
      aLines.join() + bLines.join()
    }
    lineCount shouldBe expectedLineCount

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