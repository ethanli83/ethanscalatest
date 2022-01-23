package ethanscalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers {
  "The main method" should "throw missing argument exception" in {
    assertThrows[IllegalArgumentException] {
      Main.main(Array())
    }
  }
}
