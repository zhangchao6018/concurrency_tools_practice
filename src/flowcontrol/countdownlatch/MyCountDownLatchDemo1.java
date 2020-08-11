package flowcontrol.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @Author: zhangchao
 * @Date: 8/10/20 5:57 下午
 **/
public class MyCountDownLatchDemo1 {
    public static CountDownLatch latch = new CountDownLatch(5);
    public static void main(String[] args) {

        for (int j=1;j<=7 ;j++)
        {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doOwnTask();
                    System.out.println("第"+ finalJ +"颗龙收集成功");
                    latch.countDown();
                }
            },"Thread"+j).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("集齐七颗龙珠,召唤神龙");

    }

    /**
     * 模拟不同线程执行各自任务
     */
    private static void doOwnTask() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
