package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.channels.*

final class ChannelTest extends AnyFunSuite with Matchers:
  test("scoped > fork > channel > map"):
    scoped {
      val channel = Channel[Int]()
      fork {
        channel.send(2)
        channel.send(4)
        channel.done()
      }
      val values = channel
        .map(i => i * i)
        .toList
      values.head shouldBe 4
      values.last shouldBe 16
    }

  test("supervised > fork > channel > transform"):
    supervised {
      val channel = Channel[Int]()
      fork {
        channel.send(1)
        channel.send(6)
        channel.send(8)
        channel.send(11)
        channel.done()
      }
      val values = channel
        .transform(_.filter(i => i % 2 == 0)
        .map(i => i * i))
        .toList
      values.length shouldBe 2
      values.head shouldBe 36
      values.last shouldBe 64
    }