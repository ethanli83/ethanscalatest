package ethanscalatest.counterrecord

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CounterRecordSpec extends AnyFlatSpec with Matchers {
  "parseStringToCounterRecord" should "parse string to CounterRecord" in {
    val record: CounterRecord = parseStringToCounterRecord("2016-12-01T05:00:00 5")
    record.count shouldEqual 5
    
    val datetimeString = record.timestap.format(CounterRecord.datetimePattern)
    datetimeString shouldEqual "2016-12-01T05:00:00"
  }
}
