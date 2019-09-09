package personal.nathan.disruptor.quickstart;


import com.lmax.disruptor.EventHandler;

/**
 * @author nathan.z
 * @date 2019/9/9.
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者，消费：" + orderEvent.getValue());
    }
}
