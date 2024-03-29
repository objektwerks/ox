package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class ParTest extends AnyFunSuite with Matchers:
  test("par"):
    val (a, b) = par( countFileLines(aFile), countFileLines(bFile) )
    a shouldBe aFileLineCount
    b shouldBe bFileLineCount
    a + b shouldBe expectedFileLineCount