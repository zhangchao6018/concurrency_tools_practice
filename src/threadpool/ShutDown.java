package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述：     演示关闭线程池
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);

        //1.通知关闭,新的请求将拒绝,正在执行的线程以及队列中会执行完毕
        executorService.shutdown();

        //2.立即关闭 正在执行线程会收到interrupted信号，未执行的（队列中的）会直接返回
//        executorService.shutdownNow();

        //3.未执行的->配合shutdownNow()方法使用  ->记录/重新执行
//        List<Runnable> runnableList = executorService.shutdownNow();

        //如果被shutdown执行以下语句会报错
//        executorService.execute(new ShutDownTask());

        //4.检测某个时间点是否已经终止
        boolean b = executorService.awaitTermination(1L, TimeUnit.SECONDS);
        System.out.println("检测是否停止"+b);
//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());

        //当前时间是否已经终止
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());

//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
