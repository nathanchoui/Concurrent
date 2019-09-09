package personal.nathan.jdkbase.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/10.
 */
public class ForkJoinPoolTest {

    // 带返回值
    public static class CountTask extends RecursiveTask<Long> {

        private static final int THRESHOLD = 10000;

        private long start;

        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean canCompute = (end - start) < THRESHOLD;

            if (canCompute) {
                System.out.println("canCompute");
                for (long i = start; i < end; i ++) {
                    sum += i;
                }
            }
            else {
                // 分解任务
                long step = (start + end) / 10;
                List<CountTask> subTasks = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < 10; i ++) {
                    CountTask subTask = new CountTask(pos, pos + step);
                    subTasks.add(subTask);
                    subTask.fork();
                }
                for (CountTask ct: subTasks) {
                    sum += ct.join();
                }
            }

            return sum;
        }
    }

    // 不带返回值
    public static class VoidTask extends RecursiveAction {
        @Override
        protected void compute() {

        }
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 100000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(countTask);
        try {
            long res = result.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
