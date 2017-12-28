
import org.scalatest.FunSuite

class Test_SimpleLexer extends FunSuite {
    test("Test_SimpleLexer:01"){
      
        val expected = List(
            ValTok(), Identifier("aaa"), Comma(), Identifier("bbb"), Comma(), Identifier("ccc"),
            Identifier("aaa"), Equal(), NumberLiteral("1"),
            Identifier("bbb"), Equal(), NumberLiteral("2"),
            Identifier("ccc"), Equal(), Identifier("aaa"), Plus(), Identifier("bbb"), 
            Identifier("ccc"), Equal(), Identifier("ccc"), Plus(), NumberLiteral("3"),
            Println(), Identifier("ccc")
        )

        SimpleLexer(
            """|
               |val aaa, bbb, ccc
               |aaa = 1
               |bbb = 2
               |ccc = aaa + bbb 
               |ccc = ccc + 3
               |println ccc
               |""".stripMargin) match {
            case Right(actual) => assert(expected === actual)
            case Left(error) => fail(error.toString)
        }
    }
}

