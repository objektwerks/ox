package objektwerks

import java.util.concurrent.Callable

import scala.io.{Codec, Source}
import scala.util.Using

object FileLineCountTask:
  val aLineCount = 270_562
  val bLineCount = 270_397
  val expectedLineCount = aLineCount + bLineCount

  def countLines(file: String): Int =
    Using(
      Source.fromFile(file, Codec.UTF8.name)
    ) { source =>
      source.getLines.length
    }.get

final class FileLineCountTask(file: String) extends Callable[Int]:
  def call(): Int = FileLineCountTask.countLines(file)