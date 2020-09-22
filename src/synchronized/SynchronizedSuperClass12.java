package hx.com;

/**
 * 可重入粒度测试：调用父类方法
 */
public class SynchronizedSuperClass12 {
    public synchronized void doSomething() {
        System.out.println("我是父类方法");
    }
}

class TestClass extends SynchronizedSuperClass12{
    public synchronized void doSomething() {
        System.out.println("我是子类方法方法");
        super.doSomething();
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.doSomething();
    }
}
