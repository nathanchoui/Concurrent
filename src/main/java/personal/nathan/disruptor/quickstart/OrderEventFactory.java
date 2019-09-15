package personal.nathan.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @author nathan.z
 * @date 2019/9/9.
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
//        System.out.println("return new OrderEvent instance");
        return new OrderEvent();
    }
}
