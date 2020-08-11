package flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @Author: zhangchao
 * @Date: 8/10/20 10:37 下午
 **/
public class MyConditionDemo2 {
    //这个地方用ReentrantLock接收  结果不一样

    static Lock lock = new ReentrantLock();
    //给消费者判断用
    static Condition notEmpty = lock.newCondition();
    //给生产者判断用
    static Condition notFull = lock.newCondition();

    static PriorityQueue<Integer> taskQueue = new PriorityQueue(10);

    public static void main(String[] args) {

        MyConditionDemo2 myConditionDemo2 = new MyConditionDemo2();
        MyConditionDemo2.Producer producer = myConditionDemo2.new Producer();
        MyConditionDemo2.Consumer consumer = myConditionDemo2.new Consumer();
        producer.setName("producer");
        consumer.setName("consumer");
        producer.start();
        consumer.start();
    }


    private class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (taskQueue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer poll = taskQueue.poll();
                    System.out.println("消费" + poll);
                    System.out.println("Consumer:size:" + taskQueue.size());
                    notFull.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
        private class Producer extends Thread {
            @Override
            public void run() {

                while (true) {
                    lock.lock();
                    try {
                        if (taskQueue.size() == 10) {
                            System.out.println("队列满，等待有空余");
                            try {
                                notFull.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        int i = new Random().nextInt(10);
                        taskQueue.offer(i);
                        System.out.println("生产" + i);
                        System.out.println("Producer:size:" + taskQueue.size());
                        notEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
}
