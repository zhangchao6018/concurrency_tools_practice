package flowcontrol.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 【用法二】多个线程等待某一个线程的信号，同时开始执行
 * @Author: zhangchao
 * @Date: 8/10/20 5:57 下午
 **/
public class MyCountDownLatchDemo2 {
    public static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        for (int j=1;j<=7 ;j++)
        {
            int finalJ = j;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //等待
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //等待倒计时完毕才执行
                    long now = System.currentTimeMillis();
                    doOwnTask();
                    System.out.println(now+":"+"第"+ finalJ +"颗龙收集工作开始了...");
                    latch.countDown();
                }
            };
            threadPool.submit(runnable);
        }


        System.out.println("boss不说话,任务不能开始...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("boss说:任务开始!");
        threadPool.shutdown();
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
