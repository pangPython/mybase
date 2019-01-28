import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.SelectUtils;
import org.junit.Test;


public class TestSelect {
    @Test
    public void testSelect() {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), new Column("user_name"), new Column("user_age"));
        System.out.println(select.toString());
    }

    @Test
    public void testSelect2() throws JSQLParserException {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"),"count(*)");
        System.out.println(select);
    }

    @Test
    public void testSelect3(){

        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), new LongValue("100"));
        //SELECT 100 FROM user
        System.out.println(select.toString());
        SelectUtils.addGroupBy(select,new Column("user_age"));
        System.out.println(select);
        SelectUtils.addJoin(select,new Table("user_info"));
    }
}
