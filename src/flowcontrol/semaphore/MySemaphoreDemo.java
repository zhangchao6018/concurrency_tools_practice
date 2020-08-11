package flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述:  限定版绿卡
 *
 * @Author: zhangchao
 * @Date: 8/10/20 8:28 下午
 **/
public class MySemaphoreDemo {
    static Semaphore semaphore = new Semaphore(5);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        System.out.println("疫情期间,门店营业限制数量");
        for (int j=1;j<=50 ;j++)
        {
            int finalJ = j;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("NO."+ finalJ+"拿到许可证,开始营业...");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release(1);
                    System.out.println("NO."+ finalJ+"打烊,退换许可证...");
                }
            };
            threadPool.submit(runnable);
        }
        threadPool.shutdown();
    }
}
