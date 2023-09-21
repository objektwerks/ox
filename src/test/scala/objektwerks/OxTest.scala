package objektwerks

import java.util.UUID

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*
import ox.channels.*

import FileLineCountTask.*

class OxTest extends AnyFunSuite with Matchers:
  test("scoped > fork") {
    val lineCount = scoped {
      val alines: Fork[Int] = fork( countLines("./data/data.a.csv") )
      val blines: Fork[Int] = fork( countLines("./data/data.b.csv") )
      alines.join() + blines.join()
    }
    lineCount shouldBe expectedLineCount
  }

  test("scoped > fork > scoped value") {
    val license = ForkLocal("")
    val uuid = UUID.randomUUID.toString
    val count = scoped {
      fork {
        license.scopedWhere(uuid) {
          if license.get().nonEmpty then 1 else -1
        }
      }
    }.join()
    count shouldBe 1
  }

  test("supervised > fork") {
    val lineCount = supervised {
      val alines: Fork[Int] = fork( countLines("./data/data.a.csv") )
      val blines: Fork[Int] = fork( countLines("./data/data.b.csv") )
      alines.join() + blines.join()
    }
    lineCount shouldBe expectedLineCount
  }

  test("scoped > fork > channel > map") {
    scoped {
      val channel = Channel[Int]()
      fork {
        channel.send(2)
        channel.send(4)
        channel.done()
      }
      channel
        .map(i => i * i)
        .foreach(i => println(s"*** channel map squared $i by ${Math.sqrt(i).toInt}"))
    }
  }

  test("supervised > fork > channel > transform") {
    supervised {
      val channel = Channel[Int]()
      fork {
        channel.send(6)
        channel.send(8)
        channel.done()
      }
      channel
        .transform(_.filter(i => i % 2 == 0).map(i => i * i))
        .foreach(i => println(s"*** channel transform squared $i by ${Math.sqrt(i).toInt}"))
    }
  }