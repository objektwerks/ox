package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

/**
  * See: https://ox.softwaremill.com/latest/high-level-concurrency/race.html
  */
final class RaceTest extends AnyFunSuite with Matchers:
  test("race"):
    supervised:
      val aOrb = race( countFileLines(aFile), countFileLines(bFile) )
      assert( aOrb == aFileLineCount || aOrb == bFileLineCount )

  test("race either"):
    supervised:
      val result = raceEither(
        Right( countFileLines(aFile) ),
        Left(-1)
      )
      result.map { result => result shouldBe aFileLineCount }