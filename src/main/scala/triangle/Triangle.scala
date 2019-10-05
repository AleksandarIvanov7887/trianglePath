package triangle

import scala.io.Source

object Triangle {
  type TriangleArray = Array[Array[Int]]

  def parseLines(input: String): TriangleArray = input.trim.split("\n").map(parseRow)
  private def parseRow(input: String): Array[Int] = input.trim.split("\\s+").map(_.toInt)

  def parseFile(fileName: String): (Source, TriangleArray) = {
    val source = Source.fromFile(fileName)
    val result = source.getLines.map(parseRow).toArray
    (source, result)
  }

  def minimalSum(triangleArray: TriangleArray): Int =
    triangleArray.reduceRight { (rowAbove, accumulated) =>
      rowAbove
        .zip(accumulated.zip(accumulated.tail))
        .map { case (parent, (leftChild, rightChild)) => parent + Math.min(leftChild, rightChild) }
    }.head

  def printTriangleArray(data: TriangleArray): Unit =
    data.foreach(row => {
      row.foreach(item => print(item.toString + " "))
      println
    })
}
