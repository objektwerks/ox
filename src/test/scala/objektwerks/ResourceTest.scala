package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class ResourceTest extends AnyFunSuite with Matchers:
  test("resource"):
    val fileLineCount = supervised:
      val source = useInScope(acquire(aFile))(release)
      source.getLines.length
    fileLineCount shouldBe aFileLineCount