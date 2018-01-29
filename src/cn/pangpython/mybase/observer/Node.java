package cn.pangpython.mybase.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者--集群中的一个节点
 *  当观察到主节点有信息变化时，就去重新请求主节点获取最新数据
 *  其实子节点也是被观察者，当子节点状态发生变化，主节点会更新状态
 */
public class Node implements Observer {

    private Observable observable;

    public Node(Observable observable) {
        this.observable = observable;
    }

    /**
     *
     * 观察者注册自己
     */
    public void regist(){
        observable.addObserver(this);
    }




    /**
     *
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("集群中共有:"+o.countObservers()+"个子节点");
    }
}
