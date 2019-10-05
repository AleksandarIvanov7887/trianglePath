package triangle

import scala.io.Source

object Triangle {
  type TriangleArray = Array[Array[Int]]
  case class Result(value: Int = 0, path: List[Int] = List.empty[Int])
  object Result {
    def min(a: Result, b: Result): Result = if (a.value < b.value) a else b
  }

  def parseLines(input: String): TriangleArray = input.trim.split("\n").map(parseRow)
  private def parseRow(input: String): Array[Int] = input.trim.split("\\s+").map(_.toInt)

  def parseFile(fileName: String): (Source, TriangleArray) = {
    val source = Source.fromFile(fileName)
    val result = source.getLines.map(parseRow).toArray
    (source, result)
  }

  def minimalSum(triangleArray: TriangleArray): Result = {
    val accumulator = Array.fill[Result](triangleArray.last.length + 1)(Result())
    triangleArray.foldRight(accumulator) { (rowAbove, accumulated) =>
      rowAbove
        .zip(accumulated.zip(accumulated.tail))
        .map { case (parent, (leftChild, rightChild)) =>
          val childToChoose = Result.min(leftChild, rightChild)
          Result(parent + childToChoose.value, parent :: childToChoose.path)
        }
    }.head
  }

  def printTriangleArray(data: TriangleArray): Unit =
    data.foreach(row => {
      row.foreach(item => print(item.toString + " "))
      println
    })
}
