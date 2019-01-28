package cn.pangpython.mybase.guice;

import com.google.inject.Inject;

public class UserService {
    @Inject
    private UserDao mUserDao;

    public void say() {
        mUserDao.say();
    }
}
