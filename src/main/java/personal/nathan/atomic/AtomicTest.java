package personal.nathan.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/11/3.
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicReference<long[]> atomicReference = new AtomicReference<long[]>();
        atomicReference.compareAndSet(new long[]{2L, 3L}, new long[]{2L, 3L});

        System.out.println(atomicReference.get());
    }
}
