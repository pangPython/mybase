package cn.pangpython.mybase.dbop;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatis {
    public static void main(String[] args) throws IOException {
    }

    public User getUser(int userId) throws IOException {
        String resource = "cn/pangpython/mybase/dbop/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        User user = null;
        try {
            user = (User) session.selectOne("cn.pangpython.mybase.dbop.User.selectUser", 1);
        } finally {
            session.close();
        }
        return user;
    }
}
