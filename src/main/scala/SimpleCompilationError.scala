
sealed trait SimpleCompilationError

case class SimpleLexerError(location: Location, msg: String) extends SimpleCompilationError
case class SimpleParserError(location: Location, msg: String) extends SimpleCompilationError

case class Location(line: Int, column: Int) {
  override def toString = s"$line:$column"
}
