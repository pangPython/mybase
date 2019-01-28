package cn.pangpython.mybase.designpatterns.abstractfactory;

public class AFactory implements Produce {
    @Override
    public Food get() {
        return new A();
    }
}
