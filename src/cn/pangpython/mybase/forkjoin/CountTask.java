package cn.pangpython.mybase.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *
 * fork join  框架    把大任务拆分成小任务处理
 *
 */
public class CountTask  extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    public int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute){
            for(int i = start;i<=end;i++){
                sum += i;
            }
        }else {
            int middle = (end - start)/2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);

            leftTask.fork();
            rightTask.fork();

            int resLeft = leftTask.join();
            int resRight = rightTask.join();

            sum = resLeft+resRight;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask countTask = new CountTask(1,4);
        Future<Integer> result = pool.submit(countTask);
        System.out.println(result.get());
    }
}
