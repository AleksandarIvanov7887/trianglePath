import triangle.Triangle._

object MainTest extends App {
  def assert(assertion: Boolean) {
    if (!assertion)
      throw new java.lang.AssertionError("assertion failed")
  }

  val (source, triangleFromFile) = parseFile("src/test/resources/triangleArray")
  val resultFile = minimalSum(triangleFromFile)
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
  assert(minimalSum(parseLines(triangleString)) == Result(18, List(7, 6, 3, 2)))
}
