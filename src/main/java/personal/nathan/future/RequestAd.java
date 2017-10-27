package personal.nathan.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/10/27.
 */
public class RequestAd implements Callable<String> {

    private int id;

    public RequestAd(int id) {
        this.id = id;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    public String call() throws Exception {
        // 随机停止50-100ms 模拟请求广告处理
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50) + 50);
        boolean retFlg = new Random().nextBoolean();
        if (retFlg) {
            return "thread:" + id + " proccessed completely!";
        }
        return null;
    }
}
