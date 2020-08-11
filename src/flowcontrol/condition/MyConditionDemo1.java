package flowcontrol.condition;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @Author: zhangchao
 * @Date: 8/10/20 9:26 下午
 **/
public class MyConditionDemo1 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static volatile Order order ;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Producer producer = new Producer();
        Comsumer comsumer = new Comsumer();

        threadPool.submit(producer);
        threadPool.submit(comsumer);
        threadPool.shutdown();

    }

    static class  Producer implements Runnable{
        @Override
        public void run() {
            try{
                lock.lock();
                order = new Order("123456",new BigDecimal(1).scaleByPowerOfTen(2),0);
                System.out.println("生成了订单"+order.toString()+",并通知消费者处理");
                condition.await();
                System.out.println("处理后订单"+order.toString());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class  Comsumer implements Runnable{
        @Override
        public void run() {
            try{
                SECONDS.sleep(1);
                lock.lock();
                order.setStatus(1);
                condition.signal();
                System.out.println("处理完毕,唤醒生产者");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class Order{
        private String orderId;

        private BigDecimal amount;

        private int status;

        public Order(String orderId, BigDecimal amount, int status) {
            this.orderId = orderId;
            this.amount = amount;
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", amount=" + amount +
                    ", status=" + status +
                    '}';
        }
    }

}
