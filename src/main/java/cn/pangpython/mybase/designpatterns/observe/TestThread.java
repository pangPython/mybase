package cn.pangpython.mybase.designpatterns.observe;

import java.util.Observable;

public class TestThread extends Observable implements Runnable {

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        //添加观察者 监听器
        testThread.addObserver(new TestThreadListener());
        new Thread(testThread).start();
    }

    /**
     * 告诉观察者 要重启线程
     */
    private void tellObjserverToRestart(){
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            count++;
            System.out.println(count);
            try {
                //模拟线程运行了一段时间
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                tellObjserverToRestart();
                return;
            }
            if(count == 4){

                String[] strings = new String[]{"hello","world"};

                try {
                    //产生 NPE 线程退出
                    System.out.println(strings[2]);
                }catch (Exception e){
                    e.printStackTrace();
                    tellObjserverToRestart();
                    return;
                }
            }


        }
    }
}
