package ethanscalatest

import scala.util.Success
import scala.util.Failure
import counterrecord._

object Main {
  def main(args: Array[String]): Unit = {
    require(args.length == 1, "missing output file")

    println(s"\nReading counter records from file: ${args.head}")

    val records = extractCountRecordFromOutputs(args.head)

    val totals = calculateTotals(records)
    println(s"\nTotal count: $totals")

    val dailyTotals = calculateDailyTotals(records)

    println(s"\nDaily count:")
    dailyTotals.sortBy(_._1).foreach {
      case (d, c) => println(s"Date: $d Count: $c")
    }

    val top3Records = getTopRecordsWithMostCount(records, 3)
    
    println("\nTop 3 records with most count:")
    top3Records.foreach {
      case x => println(s"Datetime: ${x.timestap} Count: ${x.count}")
    }

    println("\nDone!\n")
  }

  def calculateTotals(records: Vector[CounterRecord]) =
    records.map(_.count).reduce(_ + _) 

  def calculateDailyTotals(records: Vector[CounterRecord]) =
    records
      .groupMapReduce(_.timestap.toLocalDate)(_.count)(_ + _)
      .toVector

  def getTopRecordsWithMostCount(records: Vector[CounterRecord], take: Int) =
    records
      .sortWith(_.count > _.count)
      .take(take)
      .toVector
}
