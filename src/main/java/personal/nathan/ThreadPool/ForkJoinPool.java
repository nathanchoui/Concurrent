package personal.nathan.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/10.
 */
public class ForkJoinPool {

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
            }

            return null;
        }
    }

    // 不带返回值
    public static class VoidTask extends RecursiveAction {

        @Override
        protected void compute() {

        }
    }


    public static void main(String[] args) {

    }

}
