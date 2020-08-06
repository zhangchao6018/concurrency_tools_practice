package threadpool;

/**
 * 描述：     每创建一个线程new一次
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }

}
