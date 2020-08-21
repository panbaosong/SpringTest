package com.zhongwang.test.java8;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/21 11:12
 * @Description:
 */
public class SimpleLambda {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello Lambda!!!");
//            }
//        });

        new Thread(() -> System.out.println("Hello Lambda!!!")).start();
    }
    @FunctionalInterface
    public interface Formatter {
        void format(String name, int age);
    }
    public static class LambdaTest {

        public static void main(String[] args) {
            print((String name, int age)-> System.out.println(String.format("name:%s age:%d", name, age)), "ziyu", 18);
        }

        public static void print(Formatter formatter, String name, int age) {
            formatter.format(name, age);
        }
    }
}
