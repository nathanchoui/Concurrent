### ReentrantLock

* lock() 获得锁，若锁已经被占用，则等待。
* lockInterruptible() 获得锁，优先响应中断
* tryLock() 尝试获得锁，如果成功返回true，否则false，不等待立即返回。
* tryLock(long time, TimeUnit unit) 给定时间获得锁
* unlock() 释放锁