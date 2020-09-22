package sync;

/**
 * 同时访问一个类的不同的普通同步方法
 */
public class SynchronizedDifferentMethd7 implements Runnable {
    static SynchronizedDifferentMethd7 instance1 = new SynchronizedDifferentMethd7();

    /**
     * 由于是统一runnable,不能同时运行
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1);
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()){

        }

        System.out.println("finished");
    }


    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread-0")){
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void method1() throws InterruptedException {
        System.out.println("我是加锁的方法。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("我是没有加锁的方法。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }

}
