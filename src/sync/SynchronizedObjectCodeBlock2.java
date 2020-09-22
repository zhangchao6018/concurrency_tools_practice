package hx.com;

/**
 * 对象锁示例一，代码块形式
 */
public class SynchronizedObjectCodeBlock2 implements Runnable{
    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();
    Object lock1 = new Object();
    Object lock2 = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println("lock1，我是对象锁的代码块信息。我叫"
                    + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock1," + Thread.currentThread().getName() + "运行结束。");
        }

//        synchronized (lock1){
        synchronized (lock2){
            System.out.println("lock2，我是对象锁的代码块信息。我叫"
                    + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock2," + Thread.currentThread().getName() + "运行结束。");
        }
    }
}
