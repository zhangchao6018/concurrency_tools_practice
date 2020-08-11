package future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述：     演示批量提交任务时，用List来批量接收结果
 */
public class MultiFuturesDemo {
    public static void main(String[] args) {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int j=1;j<=10 ;j++) {
            Future<Integer> submit = pool.submit(new Mytask());
            list.add(submit);
        }

        for (Future<Integer> future : list) {
            try {
                //get是阻塞的
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }


    static class Mytask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(2);
            return new Random().nextInt(100);
        }
    }
}
