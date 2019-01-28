package cn.pangpython.mybase.guice;

import com.google.inject.Singleton;

@Singleton
public class UserDao {
    public void say() {
        System.out.println("dao is saying");
    }
}
