package sync;

/**
 * 可重入粒度测试：递归类内另外方法
 */
public class SynchronizedOhterMethod11 {

    public static void main(String[] args) {
        SynchronizedOhterMethod11 synchronizedOhterMethod11 = new SynchronizedOhterMethod11();
        synchronizedOhterMethod11.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1");
        method2();
    }
    private synchronized void method2() {
        System.out.println("这是method2");
    }
}
