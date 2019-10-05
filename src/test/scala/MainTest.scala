import triangle.Triangle._
import cats.instances.int._
import codec.CodecInstances._

object MainTest extends App {
  def assert(assertion: Boolean) {
    if (!assertion)
      throw new java.lang.AssertionError("assertion failed")
  }

  val (source, triangleFromFile) = parseFile("src/test/resources/triangleArray")
  val resultFile = minimalSum(triangleFromFile)
  println(resultFile)
  assert(resultFile.value == 468)
  assert(resultFile.path.length == 99)
  source.close()

  def triangleString: String =
    """
      |7
      |6 3
      |3 8 5
      |11 2 10 9
    """.stripMargin

  val resultString = minimalSum(parseLines(triangleString))
  assert(resultString == Result(18, List(7, 6, 3, 2)))
  println(resultString)

  println(s"Minimal path is: ${Result.toString(resultString)}")
}
