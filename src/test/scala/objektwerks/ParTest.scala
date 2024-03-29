package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.syntax.mapPar

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

  test("par either"):
    val result = parEither(
      Right( countFileLines(aFile) ),
      Left(-1)    
    )
    result.map { (count, _) => count shouldBe aFileLineCount }

  test("map par"):
    val numbers = List(1, 2, 3)
    val result = numbers.mapPar(3)( n => n * n )
    result.sum shouldBe 14

  test("foreach par"):
    val numbers = List(1, 2, 3)
    numbers.foreach( n => assert( n >= 1 ) )