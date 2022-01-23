package ethanscalatest

import scala.io.Source     
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

package object counterrecord {
  case class CounterRecord(timestap: LocalDateTime, count: Int)
  
  object CounterRecord {
    val datetimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
  }
  
  def parseStringToCounterRecord(text: String) = {
    val tokens = text.split(" ")
    val datetime = LocalDateTime.parse(tokens(0), CounterRecord.datetimePattern)
    CounterRecord(datetime, tokens(1).toInt)
  }
  
  def extractCounterRecordsFromOutputFile(filePath: String) = 
  readLinesFromFile(filePath).map(parseStringToCounterRecord)
  
  def readLinesFromFile(path: String) = {
    Source.fromFile(path).getLines.toVector
  }
}