package ethanscalatest

import scala.util.Success
import scala.util.Failure
import counterrecord._

object Main {
  def main(args: Array[String]): Unit = {
    require(args.length == 1, "missing output file")

    println("Reading counter records from file")
    val records = extractCountRecordFromOutputs(args.head)

    val totals = calculateTotals(records)
    println(s"Total counts: $totals")
  }

  def calculateTotals(records: Vector[CounterRecord]) =
    records.map(_.count).reduce(_ + _) 
}
