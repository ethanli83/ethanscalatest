package ethanscalatest

import scala.io.Source     
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.{Try, Success, Either}
import scala.util.Failure

package object counterrecord {
  case class CounterRecord(timestap: LocalDateTime, count: Int)
  
  object CounterRecord {
    val datetimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
  }
  
  def parseStringToCounterRecord(text: String) = {
    for {
      tokens <- Try(text.split(" "))
      datetime <- Try(LocalDateTime.parse(tokens(0), CounterRecord.datetimePattern))
      count <- Try(tokens(1).toInt)
    } yield CounterRecord(datetime, count)
  }
  
  def extractCounterRecordsFromOutputFile(filePath: String) = 
    readLinesFromFile(filePath) match {
      case Success(lines) => {
        val records = lines.map(parseStringToCounterRecord)
        getRecordsOrUseTheFirstError(records)
      }
      case Failure(err) => Left(err.getMessage)
    }

  def getRecordsOrUseTheFirstError(records: Vector[Try[CounterRecord]]) = {
    val failedReacords = records.filter(_.isFailure)
    if (failedReacords.length > 0) 
      Left(failedReacords.head.failed.get.getMessage) 
    else 
      Right(records.map(_.get))
  }
  
  def readLinesFromFile(path: String) = {
    Try(Source.fromFile(path).getLines.toVector)
  }
}