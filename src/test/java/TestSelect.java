import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
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

        Table userInfo = new Table("user_info");
        Select selectUserInfo = SelectUtils.buildSelectFromTable(userInfo);

        StringValue stringValue = new StringValue("user_id");
        SelectUtils.addJoin(select, userInfo, stringValue);
        System.out.println(select);
//        SelectUtils.addJoin(select,new Table("user_info"));
    }

    @Test
    public void testWhere() {
        Table user = new Table("user");
        Select select = SelectUtils.buildSelectFromTable(user);
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("user_age"));
        equalsTo.setRightExpression(new LongValue(22));

        SelectBody selectBody = new PlainSelect();
        ((PlainSelect) selectBody).setWhere(equalsTo);
        select.setSelectBody(selectBody);
        List<SelectItem> selectItemList = new ArrayList<>();
        AllTableColumns allTableColumns = new AllTableColumns(user);
        selectItemList.add(allTableColumns);
        ((PlainSelect) selectBody).setSelectItems(selectItemList);
        // SELECT user.* WHERE user_age = 22
        System.out.println(select);
    }

    @Test
    public void testWhere2() {
        Table user = new Table("user");
        Select select = SelectUtils.buildSelectFromTable(user);
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("user_age"));
        equalsTo.setRightExpression(new LongValue(22));

        GreaterThan greaterThan = new GreaterThan();
        greaterThan.setLeftExpression(new Column("user_id"));
        greaterThan.setRightExpression(new LongValue(10000));

        AndExpression andExpression = new AndExpression(equalsTo,greaterThan);

        SelectBody selectBody = new PlainSelect();
        ((PlainSelect) selectBody).setWhere(andExpression);
        select.setSelectBody(selectBody);
        List<SelectItem> selectItemList = new ArrayList<>();
        AllTableColumns allTableColumns = new AllTableColumns(user);
        selectItemList.add(allTableColumns);
        ((PlainSelect) selectBody).setSelectItems(selectItemList);
        // SELECT user.* WHERE user_age = 22 AND user_id > 10000
        System.out.println(select);

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
