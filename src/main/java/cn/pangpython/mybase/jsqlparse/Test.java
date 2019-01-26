package cn.pangpython.mybase.jsqlparse;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Database;
import net.sf.jsqlparser.schema.Server;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws JSQLParserException {

//        insert();
//        replace();
//        select4();
//        delete();
//        update();
        select();
    }

    private static void insert() {
        Insert insert = new Insert();
        insert.setTable(new Table("user"));

        ExpressionList expressionList = new ExpressionList();
        List<Expression> expressions = new ArrayList<>();
        expressions.add(new LongValue(20));
        expressionList.setExpressions(expressions);
        insert.setItemsList(expressionList);

        List<Column> columns = new ArrayList<>();
        columns.add(new Column("age"));
        insert.setColumns(columns);

        // INSERT INTO user (age) VALUES (20)
        System.out.println(insert.toString());
    }

    private static void select() {
        Select select = SelectUtils.buildSelectFromTable(new Table("user"));
        // SELECT * FROM user
        System.out.println(select.toString());
    }

    /**
     *
     */
    public static void delete() {
        Delete delete = new Delete();
        delete.setTable(new Table("user"));
        // DELETE FROM user
        System.out.println(delete);
    }

    public static void update() {
        Update update = new Update();
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("user"));
        update.setTables(tables);

        List<Column> columns = new ArrayList<>();
        columns.add(new Column("age"));
        update.setColumns(columns);

        List<Expression> expressions = new ArrayList<>();
        expressions.add(new LongValue(20));
        update.setExpressions(expressions);

        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new StringValue("user_id"));
        equalsTo.setRightExpression(new StringValue("111000011"));
        update.setWhere(equalsTo);
        // UPDATE user SET age = 20 WHERE 'user_id' = '111000011'
        System.out.println(update.toString());
    }

    public static void select2() {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("user"), new Column("a"), new Column("b"));
        System.out.println(select);
    }

    public static void select3() throws JSQLParserException {
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table("stu_score"), "math_score+english_score", "chinese_score");
        System.out.println(select);
    }

    public static void select4() {
        Server server = new Server("mysql");
        Database database = new Database(server, "abms_user");
        System.out.println(database.toString());
        Table table = new Table(database, null, "user_info");
        Alias alias = new Alias("u", true);
        table.setAlias(alias);
        System.out.println(table.toString());
    }


    private static void replace() throws JSQLParserException {
        String sql = "SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN ('11111111111111', '22222222222222');";
        Select select = (Select) CCJSqlParserUtil.parse(sql);

//Start of value modification
        StringBuilder buffer = new StringBuilder();
        ExpressionDeParser expressionDeParser = new ExpressionDeParser() {

            @Override
            public void visit(StringValue stringValue) {
                this.getBuffer().append("XXXX");
            }

        };
        SelectDeParser deparser = new SelectDeParser(expressionDeParser, buffer);
        expressionDeParser.setSelectVisitor(deparser);
        expressionDeParser.setBuffer(buffer);
        select.getSelectBody().accept(deparser);
//End of value modification


        System.out.println(buffer.toString());
//Result is: SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN (XXXX, XXXX)
    }

    public static void insert2() throws JSQLParserException {
        Insert insert = (Insert) CCJSqlParserUtil.parse("insert into user (age) values (30)");
        System.out.println(insert.toString());

        //adding a column
        insert.getColumns().add(new Column("height"));

        //adding a value using a visitor
        insert.getItemsList().accept(new ItemsListVisitor() {

            public void visit(SubSelect subSelect) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void visit(ExpressionList expressionList) {
                expressionList.getExpressions().add(new LongValue(180));
            }

            public void visit(MultiExpressionList multiExprList) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        System.out.println(insert.toString());

        //adding another column
        insert.getColumns().add(new Column("name"));

        //adding another value (the easy way)
        ((ExpressionList) insert.getItemsList()).getExpressions().add(new StringValue("pangPython"));

        System.out.println(insert.toString());
    }
}
