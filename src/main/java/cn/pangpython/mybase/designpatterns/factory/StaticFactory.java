package cn.pangpython.mybase.designpatterns.factory;

public class StaticFactory {
    public StaticFactory() {
    }

    public static Food getA(){
        return new A();
    }

    public static Food getB(){
        return new B();
    }

    public static Food getC(){
        return new C();
    }
}
