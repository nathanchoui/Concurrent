package personal.nathan.disruptor.quickstart;


import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author nathan.z
 * @date 2019/9/9.
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(100);
        System.out.println("消费者，消费：" + orderEvent.getValue());
    }
}
