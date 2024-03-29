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

  test("par limit"):
    val functions = (1 to 3).map(n => () => n * n)
    val results = parLimit(3)(functions)
    results.sum shouldBe 14