
import scala.util.parsing.input.Positional

sealed trait SimpleToken extends Positional

case class NumberLiteral(n: String) extends SimpleToken
case class Identifier(str: String) extends SimpleToken
case class ValTok() extends SimpleToken
case class Comma() extends SimpleToken
case class Equal() extends SimpleToken
case class Plus() extends SimpleToken
case class Minus() extends SimpleToken
case class Multiplication() extends SimpleToken
case class Division() extends SimpleToken
case class Println() extends SimpleToken

