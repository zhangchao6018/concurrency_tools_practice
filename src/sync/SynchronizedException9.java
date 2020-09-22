package sync;

/**
 * 方法抛异常后，会释放锁。
 * 展示不抛出异常前和抛出异常后的对比：一旦抛出了异常，
 * 第二个线程会立刻进入同步方法，意味着锁已经释放。
 */
public class SynchronizedException9 implements Runnable {
    static SynchronizedException9 instance1 = new SynchronizedException9();
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
        System.out.println("我是方法1。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
//        try {
//            throw new Exception();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        throw new RuntimeException();
        //System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("我是方法2。我叫"
                + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！\n");
    }
}
