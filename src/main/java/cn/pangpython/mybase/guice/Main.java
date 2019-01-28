package cn.pangpython.mybase.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        final Injector injector = Guice.createInjector();
        UserService instance = injector.getInstance(UserService.class);
        instance.say();
    }
}
