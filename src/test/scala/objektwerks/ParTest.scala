package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

final class ParTest extends AnyFunSuite with Matchers:
  test("par"):
    val (a, b) = par( countLines(aFile), countLines(bFile) )
    a shouldBe aLineCount
    b shouldBe bLineCount
    a + b shouldBe expectedLineCount