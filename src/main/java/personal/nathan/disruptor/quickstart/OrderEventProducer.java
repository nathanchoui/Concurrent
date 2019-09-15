package personal.nathan.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.util.UUID;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/9/13.
 */
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String msg) {
        long sequence = ringBuffer.next();
        try {
            OrderEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setValue(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("publish: " + sequence);
            ringBuffer.publish(sequence);
        }
    }
}
