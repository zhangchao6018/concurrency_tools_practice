package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述:
 *
 * @Author: zhangchao
 * @Date: 8/11/20 8:29 下午
 **/
public class MyFutureTaskDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureTask = new FutureTask<>(new MyTask());
        threadPool.submit(futureTask);

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }

    static class MyTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            return new Random().nextInt(10);
        }
    }
}
