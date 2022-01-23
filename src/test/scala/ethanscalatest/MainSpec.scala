package ethanscalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  "The calculateDailyTotals method" should "group records by date and aggregate on counts" in {
    val records = Vector(
      CounterRecord(LocalDateTime.parse("2016-12-01T05:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-01T06:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-01T07:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-02T05:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-02T06:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-03T07:00:00", CounterRecord.datetimePattern), 5),
    )

    val dailyTotals = Main.calculateDailyTotals(records).sortBy(_._1)
    dailyTotals.length shouldEqual 3

    val formatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    dailyTotals(0)._1.format(formatPattern) shouldEqual "2016-12-01"
    dailyTotals(0)._2 shouldEqual 15

    dailyTotals(1)._1.format(formatPattern) shouldEqual "2016-12-02"
    dailyTotals(1)._2 shouldEqual 10

    dailyTotals(2)._1.format(formatPattern) shouldEqual "2016-12-03"
    dailyTotals(2)._2 shouldEqual 5
  }

  "The getTopRecordsWithMostCount method" should "get top X records" in {
    val records = Vector(
      CounterRecord(LocalDateTime.parse("2016-12-01T05:00:00", CounterRecord.datetimePattern), 1),
      CounterRecord(LocalDateTime.parse("2016-12-01T06:00:00", CounterRecord.datetimePattern), 3),
      CounterRecord(LocalDateTime.parse("2016-12-01T07:00:00", CounterRecord.datetimePattern), 4),
      CounterRecord(LocalDateTime.parse("2016-12-02T05:00:00", CounterRecord.datetimePattern), 7),
      CounterRecord(LocalDateTime.parse("2016-12-02T06:00:00", CounterRecord.datetimePattern), 5),
      CounterRecord(LocalDateTime.parse("2016-12-03T07:00:00", CounterRecord.datetimePattern), 2),
    )

    val top3Records = Main.getTopRecordsWithMostCount(records, 3)
    top3Records.length shouldEqual 3

    top3Records(0).count shouldEqual 7
    top3Records(1).count shouldEqual 5
    top3Records(2).count shouldEqual 4
  }
}
