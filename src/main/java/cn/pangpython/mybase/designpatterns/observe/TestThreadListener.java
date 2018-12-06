package cn.pangpython.mybase.designpatterns.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者 线程监听器
 */
public class TestThreadListener implements Observer {
    /**
     * 状态发生变化 接到通知时 就执行此方法
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        TestThread testThread = new TestThread();
        testThread.addObserver(this);
        new Thread(testThread).start();
    }
}
