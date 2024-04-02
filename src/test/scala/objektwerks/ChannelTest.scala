package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.channels.*

final class ChannelTest extends AnyFunSuite with Matchers:
  test("scoped > fork > channel > map"):
    scoped:
      val channel = Channel.buffered[Int](capacity = 3)
      fork:
        channel.send(2)
        channel.send(4)
        channel.done()
      val values = channel
        .map(i => i * i)
        .toList
      values.head shouldBe 4
      values.last shouldBe 16

  test("supervised > fork > channel > transform"):
    supervised:
      val channel = Channel.buffered[Int](capacity = 4)
      fork:
        channel.send(1)
        channel.send(6)
        channel.send(8)
        channel.send(11)
        channel.done()
      val values = channel
        .transform(_.filter(i => i % 2 == 0)
        .map(i => i * i))
        .toList
      values.length shouldBe 2
      values.head shouldBe 36
      values.last shouldBe 64

  test("source"):
    supervised:
      val source = Source.fromValues(1, 2, 3)
      source.map(i => i * 2).toList.sum shouldBe 12

  test("select"):
    import scala.annotation.tailrec

    val result = supervised:
      val letters = Source.fromValues("a", "bb", "ccc")
      val numbers = Source.fromValues(1, 2, 3)

      @tailrec
      def consume(acc: Int): Int =
        selectSafe(letters, numbers) match
          case letter: String =>
            println(letter)
            println(acc + letter.length)
            consume(acc + letter.length)
          case number: Int =>
            println(number)
            println(acc + number)
            consume(acc + number)
          case _ => acc

      consume(0)
    result shouldBe 6