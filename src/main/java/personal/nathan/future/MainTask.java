package personal.nathan.future;

import java.util.concurrent.*;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/10/27.
 */
public class MainTask {

    /**
     * 模拟情况说明：
     * 请求三个广告，其中有一个是主广告位，其次两个是副广告位。
     * 如果主广告为空，其它两个任务则放弃；
     * 如果主广告不为空，则其它两个广告位在超时时间以内获取，成功则拼接返回。
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        RequestAd ad1 = new RequestAd(1);
        RequestAd ad2 = new RequestAd(2);
        RequestAd ad3 = new RequestAd(3);
        FutureTask<String> task1 = new FutureTask<String>(ad1);
        FutureTask<String> task2 = new FutureTask<String>(ad2);
        FutureTask<String> task3 = new FutureTask<String>(ad3);
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.shutdown();
        String mainRes = null;
        String res2 = null;
        String res3 = null;
        try {
            mainRes = task1.get();
            res2 = null;
            res3 = null;
            if (mainRes != null) {
                try {
                    res2 = task2.get(5, TimeUnit.MILLISECONDS);
                }
                catch (TimeoutException te) {
                    te.printStackTrace();
                }
                try {
                    res3 = task2.get(10, TimeUnit.MILLISECONDS);
                }
                catch (TimeoutException te) {
                    te.printStackTrace();
                }
            }
            else {
                task2.cancel(true);
                task3.cancel(true);
            }
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        catch (ExecutionException ee) {
            ee.printStackTrace();
        }
        System.out.println("========================================");
        System.out.println(mainRes + "   " + (res2 == null ? "": res2) + "   " + (res3 == null ? "": res3));
        System.out.println("========================================");

    }
}
