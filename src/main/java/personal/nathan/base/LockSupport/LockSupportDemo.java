package personal.nathan.base.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/7.
 */
public class LockSupportDemo {

    static final Object o = new Object();

    static class Park implements Runnable {
        @Override
        public void run() {
            LockSupport.park(o);
        }
    }

}
