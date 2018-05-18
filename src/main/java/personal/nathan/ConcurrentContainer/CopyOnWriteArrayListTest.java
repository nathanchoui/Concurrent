package personal.nathan.ConcurrentContainer;

/**
 * 读-读 不上锁，非常适合在大量读，少量写的场景。
 *
 * 读操作，不会加锁。因为在写的时候，会复制一份，并对复制的那份进行写，
 * 写完后再替换原来的数据，这个操作是原子的，所以读到的数据不必加锁也能保证安全性。
 *
 * Created by zhangwei on 2018/5/16.
 */
public class CopyOnWriteArrayListTest {

}
