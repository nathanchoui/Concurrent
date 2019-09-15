package personal.nathan.disruptor.quickstart3;

import com.lmax.disruptor.EventFactory;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/9/15.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
