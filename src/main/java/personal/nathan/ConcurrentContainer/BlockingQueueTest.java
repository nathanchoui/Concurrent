package personal.nathan.ConcurrentContainer;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/18.
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        // 有界阻塞队列
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 4; i++) {
            // offer如果队列已满，马上返回false
            //abq.offer(String.valueOf(i));

            //
            // 如果已满则会waiting，直到队列有空间
            abq.put(String.valueOf(i));
        }
        abq.poll();
        abq.forEach(System.out::println);
    }

}
