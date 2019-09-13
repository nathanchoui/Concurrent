package personal.nathan.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nathan.z
 * @date 2019/9/10.
 */
public class DisruptorMain {

    /**
     * public Disruptor(
     *             final EventFactory<T> eventFactory,
     *             final int ringBufferSize,
     *             final ThreadFactory threadFactory,
     *             final ProducerType producerType,
     *             final WaitStrategy waitStrategy)
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        EventFactory<OrderEvent> orderEventFactory = new OrderEventFactory();
        final int ringBufferSize = 1024 * 1024;
        ThreadFactory threadFactory = new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                int c = atomicInteger.incrementAndGet();
                System.out.println("create no " + c + " Threads");
                //通过计数器，可以更好的管理线程
                return new Thread(new Worker());
            }
        };
        WaitStrategy waitStrategy = new BlockingWaitStrategy();
        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE, waitStrategy);

    }

    private static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is executing.");
        }
    }

}
