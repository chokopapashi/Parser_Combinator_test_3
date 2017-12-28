
import scala.util.parsing.combinator.RegexParsers

object SimpleLexer extends RegexParsers {
    override def skipWhitespace = true
//  override val whiteSpace = """[ \t\r\f]+""".r

    def val_tok     = positioned("val" ^^ (_ => ValTok()))
    def comma       = positioned("," ^^ (_ => Comma()))
    def equal       = positioned("=" ^^ (_ => Equal()))
    def plus        = positioned("+" ^^ (_ => Plus()))
    def minus       = positioned("-" ^^ (_ => Minus()))
    def multiplication = positioned("-" ^^ (_ => Multiplication()))
    def division    = positioned("/" ^^ (_ => Division()))
    def println_tok = positioned("println" ^^ (_ => Println()))
    def number_literal = positioned("[0-9]+".r ^^ {n => NumberLiteral(n)})
    def identifier  = positioned("[a-zA-Z_][a-zA-Z0-9_]+".r ^^ {s => Identifier(s)})

    def tokens: Parser[List[SimpleToken]] =
        phrase(rep1(val_tok | comma | equal | plus | minus | multiplication | division | println_tok | number_literal | identifier))

    def apply(code: String): Either[SimpleLexerError, List[SimpleToken]] = {
        parse(tokens, code) match {
            case NoSuccess(msg, next)  => Left(SimpleLexerError(Location(next.pos.line, next.pos.column), msg))
            case Success(result, next) => Right(result)
        }
    }
}

