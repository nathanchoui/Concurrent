package personal.nathan.excercise.I1_12;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/11/4.
 */
public class PrintThread extends Thread {

    private String message;

    public PrintThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i ++) {
            System.out.print(message);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintThread tPlus = new PrintThread("+");
        PrintThread tMinus = new PrintThread("-");
        tPlus.start();
        tPlus.join();
        tMinus.start();
    }
}
