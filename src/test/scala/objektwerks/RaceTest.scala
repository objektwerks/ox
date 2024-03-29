package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class RaceTest extends AnyFunSuite with Matchers:
  test("race"):
    val aOrb = race( countFileLines(aFile), countFileLines(bFile) )
    assert( aOrb == aFileLineCount || aOrb == bFileLineCount )