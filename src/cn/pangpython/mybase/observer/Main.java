package cn.pangpython.mybase.observer;

public class Main {

    public static void main(String[] args) {
        Master master = new Master();

        for(int i =0;i<10;i++){
            Thread thread = new Thread(() -> {
                Node node = new Node(master);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                node.regist();
            });
            thread.start();

        }



    }
}
