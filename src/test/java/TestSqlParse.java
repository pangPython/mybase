import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.util.List;

public class TestSqlParse {
    @Test
    public void testSqlParse() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM user");
        System.out.println(stmt.toString());
    }

    @Test
    public void multiSql() throws JSQLParserException {
        Statements stmt = CCJSqlParserUtil.parseStatements("SELECT * FROM tab1; SELECT * FROM tab2");
        System.out.println(stmt);
    }

    @Test
    public void express() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("a*(5+mycolumn)");
        System.out.println(expr);

    }

    @Test
    public void test() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("SELECT * FROM MY_TABLE1;");
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
        System.out.println(tableList);


    }

    @Test
    public void test2() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse("select a,b,c from test");
        final AddAliasesVisitor instance = new AddAliasesVisitor();
        select.getSelectBody().accept(instance);
        System.out.println(select);
    }
}
