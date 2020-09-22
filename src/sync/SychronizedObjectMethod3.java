package sync;

/**
 * 示例二————方法锁
 */
public class SychronizedObjectMethod3 implements Runnable{
    static SychronizedObjectMethod3 instance = new SychronizedObjectMethod3();
    static SychronizedObjectMethod3 instance2 = new SychronizedObjectMethod3();

    public static void main(String[] args) {
        //作用于方法  锁就是instance的实例对象
        //1.同一个实例  互斥
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){ }
        System.out.println("finished");
        //2.不是同一个实例,因此不是互斥的
        Thread t3 = new Thread(instance);
        Thread t4 = new Thread(instance2);
        t3.start();
        t4.start();
        while (t3.isAlive() || t4.isAlive()){}
        System.out.println("finished2");
    }
    @Override
    public void run() {
        method();
    }

    public synchronized void method(){
        System.out.println("我是对象锁的方法修饰符形式，我叫"
                + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }
}
