import triangle.Triangle._

object MainTest extends App {
  def assert(assertion: Boolean) {
    if (!assertion)
      throw new java.lang.AssertionError("assertion failed")
  }

  val (source, triangleFromFile) = parseFile("src/test/resources/triangleArray")
  assert(minimalSum(triangleFromFile) == 468)
  source.close()

  def triangleString: String =
    """
      |7
      |6 3
      |3 8 5
      |11 2 10 9
    """.stripMargin
  assert(minimalSum(parseLines(triangleString)) == 18)
}
