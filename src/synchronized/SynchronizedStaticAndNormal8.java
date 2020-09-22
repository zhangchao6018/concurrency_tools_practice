package hx.com;

/**
 * 同时访问静态synchronized和非静态synchronized方法
 * 结果：非同步。因为一个锁对象为class 一个所对象为this
 */
public class SynchronizedStaticAndNormal8 implements Runnable{
    static SynchronizedStaticAndNormal8 instance1 = new SynchronizedStaticAndNormal8();
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

    public synchronized static void method1() throws InterruptedException {
        System.out.println("我是静态加锁的方法1。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("我是非静态加锁的方法2。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }
}
