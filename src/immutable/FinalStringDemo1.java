package immutable;

/**
 * 描述：     TODO
 */
public class FinalStringDemo1 {

    public static void main(String[] args) {
        //a指向常量池
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        //final修饰的b永远不会变化相当于常量---编译时已确定
        //c指向常量池
        String c = b + 2;
        //运行时才能确定--在堆上生成
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
