package cn.pangpython.mybase.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 *
 * 主节点 被观察者 当主节点有信息变化时，子节点会更新状态
 * 其实主节点也是观察者身份，子节点也是被观察者，观察着子节点的变化
 *
 */
public class Master extends Observable {
    private Vector<Observer> observers;//线程安全的容器

    public Master() {
        observers = new Vector<>();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    @Override
    public synchronized void deleteObservers() {
        observers.clear();
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    protected synchronized void clearChanged() {
        super.clearChanged();
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged();
    }

    @Override
    public synchronized int countObservers() {
        return observers.size();
    }
}
