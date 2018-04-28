package personal.nathan.base.StopThread;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/23.
 */
public class ThreadStateStudy {

    public static void main(String[] args) {
        // 刚new出来的线程状态是new
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("new出来的线程状态： " + t.getState());
        // 调用start之后，状态是runnable，不区分是否正在运行，具体什么时候运行，得看操作系统调度。
        t.start();
        System.out.println("线程调用start以后： " + t.getState());


    }
}
