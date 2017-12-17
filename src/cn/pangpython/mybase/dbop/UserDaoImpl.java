package cn.pangpython.mybase.dbop;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserDaoImpl {
    private SqlSessionFactory sqlSessionFactory = null;

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId(3);
        user.setName("test");

        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.insertUser(user)) {
            System.out.println("user入库成功！");
        } else {
            System.out.println("user入库失败！");
        }

        user.setName("updateUserName");
        if(userDao.updateUser(user)){
            System.out.println("user更新成功！");
        }else{
            System.out.println("user更新失败！");
        }

        if(userDao.deleteUser(user)){
            System.out.println("user删除成功！");
        }else{
            System.out.println("user删除失败！");
        }

    }

    /**
     * 再数据库中获取一个User
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public User getUser(int userId) throws IOException {

        SqlSession session = sqlSessionFactory.openSession();
        User user = null;
        try {
            user = (User) session.selectOne("cn.pangpython.mybase.dbop.User.selectUser", 1);
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * 往数据库添加一个user
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        boolean flg = false;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int res = sqlSession.insert("cn.pangpython.mybase.dbop.User.insertUser", user);
        System.out.println("插入了" + res + "条记录");
        if (res > 0) {
            flg = true;
        }
        sqlSession.close();
        return flg;
    }

    /**
     * 更新user
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        boolean flg = false;
        int res = sqlSession.update("cn.pangpython.mybase.dbop.User.updateUser",user);
        if(res > 0 ){
            flg = true;
        }
        sqlSession.close();
        return flg;
    }

    /**
     * 从数据库删除一个user
     * @param user
     * @return
     */
    public boolean deleteUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        boolean flg = false;
        int res = sqlSession.delete("cn.pangpython.mybase.dbop.User.deleteUser",user);
        if(res > 0 ){
            flg = true;
        }
        sqlSession.close();
        return flg;
    }


    public UserDaoImpl() throws IOException {
        String resource = "cn/pangpython/mybase/dbop/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
}
