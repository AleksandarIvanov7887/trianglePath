package triangle

import cats.{Monoid, Order}
import cats.implicits._
import codec.Codec

import scala.reflect.ClassTag
import scala.io.Source

object Triangle {
  type TriangleArray[A] = Array[Array[A]]

  case class Result[A](value: A, path: List[A] = List.empty[A])
  object Result {
    def min[A](a: Result[A], b: Result[A])(implicit order: Order[A]): Result[A] =
      if (a.value < b.value) a else b

    def toString[A](a: Result[A])(implicit codec: Codec[A]): String = {
      val stringPath = a.path.map(codec.encode).reduce((a, b) => s"$a + $b")
      stringPath + s" = ${codec.encode(a.value)}"
    }
  }

  def parseLines[A: ClassTag](input: String)(implicit codec: Codec[A]): TriangleArray[A] =
    input.trim.split("\n").map(parseRow(_))

  private def parseRow[A: ClassTag](input: String)(implicit codec: Codec[A]): Array[A] =
    input.trim.split("\\s+").map(codec.decode)

  def parseFile[A: ClassTag](fileName: String)(implicit codec: Codec[A]): (Source, TriangleArray[A]) = {
    val source = Source.fromFile(fileName)
    val result = source.getLines.map(a => parseRow(a)).toArray
    (source, result)
  }

  def minimalSum[A](triangleArray: TriangleArray[A])(implicit monoid: Monoid[A], order: Order[A]): Result[A] = {
    val accumulator = Array.fill[Result[A]](triangleArray.last.length + 1)(Result(monoid.empty))
    triangleArray.foldRight(accumulator) { (rowAbove, accumulated) =>
      rowAbove
        .zip(accumulated.zip(accumulated.tail))
        .map { case (parent, (leftChild, rightChild)) =>
          val childToChoose = Result.min(leftChild, rightChild)
          Result(monoid.combine(parent, childToChoose.value), parent :: childToChoose.path)
        }
    }.head
  }

  def printTriangleArray[A](data: TriangleArray[A])(implicit codec: Codec[A]): Unit =
    data.foreach(row => {
      row.foreach(item => print(codec.encode(item) + " "))
      println
    })
}
