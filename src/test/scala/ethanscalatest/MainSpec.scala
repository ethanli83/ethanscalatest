package ethanscalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDateTime;
import counterrecord._

class MainSpec extends AnyFlatSpec with Matchers {
  "The main method" should "throw missing argument exception" in {
    assertThrows[IllegalArgumentException] {
      Main.main(Array())
    }
  }

  "The calculateTotals method" should "return sum of counts" in {
    val records = Vector(
      CounterRecord(LocalDateTime.now(), 1),
      CounterRecord(LocalDateTime.now(), 3),
      CounterRecord(LocalDateTime.now(), 5),
    )

    val totals = Main.calculateTotals(records)
    totals shouldEqual 9
  }
}
