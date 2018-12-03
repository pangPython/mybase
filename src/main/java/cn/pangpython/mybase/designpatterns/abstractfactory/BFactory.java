package cn.pangpython.mybase.designpatterns.abstractfactory;

public class BFactory implements Produce {
    @Override
    public Food get() {
        return new B();
    }
}
