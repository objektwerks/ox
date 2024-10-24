package objektwerks

import scala.io.{Codec, Source}
import scala.util.Using

object FileLineCount:
  val aFile = "./data/data.a.csv"
  val bFile = "./data/data.b.csv"
  val aFileLineCount = 270_562
  val bFileLineCount = 270_397
  val expectedFileLineCount = aFileLineCount + bFileLineCount

  def countFileLines(file: String): Int =
    Using(
      Source.fromFile(file, Codec.UTF8.name)
    ) { source =>
      source.getLines.length
    }.get

  def acquire(file: String): Source = Source.fromFile(file, Codec.UTF8.name)

  def release(source: Source): Unit = source.close()