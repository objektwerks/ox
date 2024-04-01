package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.*

final class ResourceTest extends AnyFunSuite with Matchers:
  test("resource"):
    supervised:
      val resource1 = useInScope(acquire(10))(release)
      println(s"Using $resource1 ...")
