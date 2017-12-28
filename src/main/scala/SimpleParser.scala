
import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

object SimpleParser extends Parsers {
    override type Elem = SimpleToken

    class SimpleTokenReader(tokens: Seq[SimpleToken]) extends Reader[SimpleToken] {
        override def first: SimpleToken = tokens.head
        override def atEnd: Boolean = tokens.isEmpty
        override def pos: Position = tokens.headOption.map(_.pos).getOrElse(NoPosition)
        override def rest: Reader[SimpleToken] = new SimpleTokenReader(tokens.tail)
    }

    def statement: Parser[WorkflowAST] = positioned {
        val exit = EXIT() ^^ (_ => Exit)
        val readInput = READINPUT() ~ rep(identifier ~ COMMA()) ~ identifier ^^ {
            case read ~ inputs ~ IDENTIFIER(lastInput) => ReadInput(inputs.map(_._1.str) ++ List(lastInput))
        }
        val callService = CALLSERVICE() ~ literal ^^ {
            case call ~ LITERAL(serviceName) => CallService(serviceName)
        }
        val switch = SWITCH() ~ COLON() ~ INDENT() ~ rep1(ifThen) ~ opt(otherwiseThen) ~ DEDENT() ^^ {
            case _ ~ _ ~ _ ~ ifs ~ otherwise ~ _ => Choice(ifs ++ otherwise)
        }
        exit | readInput | callService | switch
    }

    def block: Parser[WorkflowAST] = positioned {
        rep1(statement) ^^ { case stmtList => stmtList reduceRight AndThen }
    }

    def program: Parser[WorkflowAST] = positioned {
        phrase(block)
    }
}

