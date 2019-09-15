package personal.nathan.disruptor.quickstart3;

import com.lmax.disruptor.EventHandler;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/9/15.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getValue());
    }
}
