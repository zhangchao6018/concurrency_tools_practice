package immutable;

/**
 * 描述：     final的方法
 */
public class FinalMethodDemo {

    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {
        System.out.println("父类的sleep");
    }
}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
        eat();
    }

//    public final void eat() {
//
//    }
    //其实不是重写
    public static void sleep() {
        System.out.println("子类的sleep");
    }

    public static void main(String[] args) {
//        new SubClass().sleep();
        SubClass.sleep();
    }
}