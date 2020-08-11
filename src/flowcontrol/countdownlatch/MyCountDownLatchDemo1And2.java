package flowcontrol.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 【用法一/二结合】
 * @Author: zhangchao
 * @Date: 8/10/20 5:57 下午
 **/
public class MyCountDownLatchDemo1And2 {
    public static CountDownLatch beginLatch = new CountDownLatch(1);
    public static CountDownLatch endLatch = new CountDownLatch(7);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        for (int j=1;j<=7 ;j++)
        {
            int finalJ = j;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //等待裁判执行命令
                    System.out.println("第"+ finalJ +"号运动员就绪...");
                    try {
                        beginLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //等待倒计时完毕才执行
                    long now = System.currentTimeMillis();
                    doOwnTask();
                    System.out.println(now+":"+"第"+ finalJ +"到达终点...");
                    endLatch.countDown();
                }
            };
            threadPool.submit(runnable);
        }


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        beginLatch.countDown();
        System.out.println("裁判:比赛开始...");

        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("裁判:所有运动员到达终点,比赛结束...");
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
