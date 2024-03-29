package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCountTask.*

final class ParTest extends AnyFunSuite with Matchers:
  test("par"):
    val (a, b) = par( countLines("./data/data.a.csv"), countLines("./data/data.b.csv") )
    a + b shouldBe expectedLineCount