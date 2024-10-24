package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

import FileLineCount.*

/**
  * See:
  * 1. https://ox.softwaremill.com/latest/high-level-concurrency/par.html
  * 2. https://ox.softwaremill.com/latest/high-level-concurrency/collections.html
  */
final class ParTest extends AnyFunSuite with Matchers:
  test("par"):
    supervised:
      val (a, b) = par( countFileLines(aFile), countFileLines(bFile) )
      a shouldBe aFileLineCount
      b shouldBe bFileLineCount
      a + b shouldBe expectedFileLineCount

  test("par limit"):
    supervised:
      val functions = (1 to 3).map(n => () => n * n)
      val results = parLimit(2)(functions)
      results.sum shouldBe 14

  test("par either"):
    supervised:
      val result = parEither(
        Right( countFileLines(aFile) ),
        Left(-1)
      )
      result.map { (count, _) => count shouldBe aFileLineCount }

  test("map par"):
    supervised:
      val numbers = List(1, 2, 3)
      val result = numbers.mapPar(2)( n => n * n )
      result.sum shouldBe 14

  test("foreach par"):
    supervised:
      val numbers = List(1, 2, 3)
      numbers.foreach( n => assert( n >= 1 ) )

  test("filter par"):
    supervised:
      val numbers = List(1, 2, 3)
      val result = numbers.filterPar(2)(_ % 2 == 0)
      result.sum shouldBe 2

  test("collect par"):
    supervised:
      val input = List(1, 2, 3)
      val result = input.collectPar(2) {
        case i if i % 2 == 0 => i * i
      }
      result.sum shouldBe 4