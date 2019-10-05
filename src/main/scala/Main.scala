import triangle.Triangle._
import cats.instances.int._
import codec.CodecInstances._

object Main {

  def main(args: Array[String]): Unit = {
    val parsedArray = parseLines(args.head)

    val result = minimalSum(parsedArray)

    println(s"Minimal path is: ${Result.toString(result)}")
  }
}
