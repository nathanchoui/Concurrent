/**
 * Description:
 * 基本调整选项
 * 上面的代码已经可以处理大多数的情况了，
 * 但是在有的时候还是会需要根据不同的软件或者硬件来调整选项以获得更高的性能。
 * 基本的选项有两个：单或者多生产者模式和可选的等待策略。
 *
 * 单或多 事件生产者
 * 在并发系统中提高性能最好的方式之一就是单一写者原则，
 * 对Disruptor也是适用的。如果在你的代码中仅仅有一个事件生产者，
 * 那么可以设置为单一生产者模式来提高系统的性能。
 *
 *
 * 可选的等待策略
 * Disruptor默认的等待策略是BlockingWaitStrategy。
 * 这个策略的内部适用一个锁和条件变量来控制线程的执行和等待（Java基本的同步方法）。
 * BlockingWaitStrategy是最慢的等待策略，但也是CPU使用率最低和最稳定的选项。
 * 然而，可以根据不同的部署环境调整选项以提高性能。
 *
 * SleepingWaitStrategy
 * 和BlockingWaitStrategy一样，SpleepingWaitStrategy的CPU使用率也比较低。
 * 它的方式是循环等待并且在循环中间调用LockSupport.parkNanos(1)来睡眠，
 * （在Linux系统上面睡眠时间60µs）.然而，它的优点在于生产线程只需要计数，而不执行任何指令。
 * 并且没有条件变量的消耗。但是，事件对象从生产者到消费者传递的延迟变大了。
 * SleepingWaitStrategy最好用在不需要低延迟，而且事件发布对于生产者的影响比较小的情况下。比如异步日志功能。
 *
 * YieldingWaitStrategy
 * YieldingWaitStrategy是可以被用在低延迟系统中的两个策略之一，
 * 这种策略在减低系统延迟的同时也会增加CPU运算量。
 * YieldingWaitStrategy策略会循环等待sequence增加到合适的值。
 * 循环中调用Thread.yield()允许其他准备好的线程执行。如果需要高性能而且事件消费者线程比逻辑内核少的时候，推荐使用YieldingWaitStrategy策略。例如：在开启超线程的时候。
 *
 * BusySpinWaitStrategy
 * BusySpinWaitStrategy是性能最高的等待策略，同时也是对部署环境要求最高的策略。
 * 这个性能最好用在事件处理线程比物理内核数目还要小的时候。例如：在禁用超线程技术的时候。
 *
 *
 *
 *
 *
 * <p>
 * Created by nathan.z on 2019/9/15.
 */
package personal.nathan.disruptor.quickstart3;