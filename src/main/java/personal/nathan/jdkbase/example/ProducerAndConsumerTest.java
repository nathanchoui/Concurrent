package personal.nathan.jdkbase.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/22.
 */
public class ProducerAndConsumerTest {

    /**
     * 数据容器
     *
     * 生产者与消费者的缓冲池
     *
     */
    private static BlockingQueue<Data> queue = new LinkedBlockingQueue<>();

    private static AtomicInteger counter = new AtomicInteger();

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Data data = queue.take();
                    System.out.println("consumed: " + data);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                Data data = new Data(counter.getAndIncrement(), Thread.currentThread().getName());
                try {
                    queue.put(data);
                    System.out.println("produced: " + data);
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 数据结构
     */
    public static class Data {
        private int id;
        private String content;

        public Data(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Data{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
        }

        public static void main(String[] args) {
            ExecutorService es = Executors.newFixedThreadPool(4);
            Runnable c = new Consumer();
            Runnable p = new Producer();
            for (int i = 0; i < 2; i ++) {
//                Thread cosumer = new Thread(c, "consumer" + i + 1);
//                Thread producer = new Thread(p);
                es.submit(c);
                es.submit(p);
            }
            es.shutdown();

        }
    }

}
