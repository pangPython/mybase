import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;


public class TestSelect {
    @Test
    public void testSelect() {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), new Column("user_name"), new Column("user_age"));
        System.out.println(select.toString());
    }

    @Test
    public void testSelect2() throws JSQLParserException {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), "count(*)");
        System.out.println(select);
    }

    @Test
    public void testSelect3() {

        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), new LongValue("100"));
        //SELECT 100 FROM user
        System.out.println(select.toString());
        SelectUtils.addGroupBy(select, new Column("user_age"));
        System.out.println(select);
//        SelectUtils.addJoin(select,new Table("user_info"));
    }

    @Test
    public void testSelect4() {
        CCJSqlParserManager pm = new CCJSqlParserManager();

        String sql = "SELECT 1,a.*,TRIM(b.task_name) AS task_name1 FROM task a JOIN task_running_sts b ON a.id=b.task_id;";

        Statement statement;
        try {
            statement = pm.parse(new StringReader(sql));
            if (statement instanceof Select) {
                Select selectStatement = (Select) statement;
                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                List tableList = tablesNamesFinder.getTableList(selectStatement);
                for (Iterator iter = tableList.iterator(); iter.hasNext(); ) {
                    System.out.println(iter.next());
                }

            }
        } catch (JSQLParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
