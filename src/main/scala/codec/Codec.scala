package codec

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A
}

object CodecInstances {
  implicit val intCodec: Codec[Int] = new Codec[Int] {
    override def encode(value: Int): String = value.toString
    override def decode(value: String): Int = value.toInt
  }
}