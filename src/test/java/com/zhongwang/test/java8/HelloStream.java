package com.zhongwang.test.java8;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/20 14:19
 * @Description: java8里的新特性Stream
 */
public class HelloStream {
    private static List<Apple> list = new ArrayList<Apple>();

    @BeforeClass
    public static void init(){
        list.add(new Apple(1,"red",100,"湖南"));
        list.add(new Apple(2,"red",200,"湖南"));
        list.add(new Apple(3,"green",400,"湖南"));
        list.add(new Apple(4,"green",50,"天津"));
        list.add(new Apple(5,"green",80,"天津"));
        list.add(new Apple(6,"green",90,"湖北"));
    }
    /**
    * @Description: 找出红色的苹果
    * @Param: []
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/20
    */
    @Test
    public void test1(){
        for(Apple apple:list){
            if (apple.getColor().equals("red")) {
                System.out.println(apple);
            }
        }
    }
    /**
    * @Description: 使用Stream来找出红色的苹果 重量大于300的
    * @Param: []
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/20
    */
    @Test
    public void test2(){
        List<Apple> red = list.stream().filter(a -> a.getColor().equals("red"))
                .filter(a->a.getWeight()>100)
                .collect(Collectors.<Apple>toList());
        for(Apple apple:red){
            System.out.println(apple);
        }
    }
    /**
    * @Description: 根据颜色分组，求出每个颜色的平均重量
    * @Param: []
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/21
    */
    @Test
    public void test3(){
        list.stream()
                .collect(Collectors.groupingBy(a -> a.getColor(),//基于颜色分组
                        Collectors.averagingInt(a->a.getWeight())))//统计平均数量
                .forEach((k,v)-> System.out.println(k+":"+v));//打印
    }
    /**
    * @Description: map映射 将挑选出的apple实体映射为weight字符串
    * @Param: []  另外还提供了mapToDouble mapToInt mapToLong这些映射分别返回对应类型的流
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/21
    */
    @Test
    public void test4(){
        list.stream().filter(a->a.getColor().equals("red"))
                .map(Apple::getOrigin).collect(Collectors.toList())
                .forEach((k)-> System.out.println(k));
    }

    /**
    * @Description: 颜色为红色的苹果的总重量
    * @Param: []
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/21
    */
    @Test
    public void test5(){
        System.out.println(list.stream().filter(a -> a.getColor().equals("red")).mapToInt(Apple::getWeight).sum());
    }
    @Test
    public void test6(){
        String [] args = {"java","php","c++","c#","Python"};
        List<String[]> collect = Arrays.stream(args).map(str -> str.split("")).distinct().collect(Collectors.toList());
        System.out.println(collect.size());
        for (String[] strings : collect) {
            System.out.println("--------------------------------------------------");
            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }
        }
    }

    @Test
    public void test7(){
        String [] args = {"java","php","c++","c#","Python"};

        List<String> collect = Arrays.stream(args).map(str -> str.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        collect.stream().forEach(k-> System.out.println(k));
    }
}
