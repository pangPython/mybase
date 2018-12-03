package cn.pangpython.mybase.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //必须创建时明确大小
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        System.out.println(queue.size());
        queue.add(0);
        System.out.println(queue.size());
        int i = queue.take();
        System.out.println(i);

    }
}
