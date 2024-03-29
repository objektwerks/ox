package objektwerks

import scala.io.{Codec, Source}
import scala.util.Using

object FileLineCount:
  val aFile = "./data/data.a.csv"
  val bFile = "./data/data.b.csv"
  val aLineCount = 270_562
  val bLineCount = 270_397
  val expectedLineCount = aLineCount + bLineCount

  def countLines(file: String): Int =
    Using(
      Source.fromFile(file, Codec.UTF8.name)
    ) { source =>
      source.getLines.length
    }.get