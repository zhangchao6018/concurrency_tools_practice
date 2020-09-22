package sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 用Lock的使用来类比synchronized
 *
 * @Author: zhangchao
 **/
public class SynchronizedToLock13 {
    private static Lock lock = new ReentrantLock();
    //synchronized 内部其实类似lock,也是分为了加锁,解锁的操作
    public synchronized void method1(){
        System.out.println("synchronized形式的锁");
    }

    public void method2(){
        lock.lock();
        try {
            System.out.println("lock形式的锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 lock13 = new SynchronizedToLock13();
        lock13.method1();
        lock13.method2();
    }
}
