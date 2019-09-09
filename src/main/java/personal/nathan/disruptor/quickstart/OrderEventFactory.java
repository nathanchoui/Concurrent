package personal.nathan.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @author nathan.z
 * @date 2019/9/9.
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setValue("test order event");
        return orderEvent;
    }
}
