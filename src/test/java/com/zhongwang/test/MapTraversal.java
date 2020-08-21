package com.zhongwang.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/19 14:20
 * @Description: Map的四种遍历方式
 */
public class MapTraversal {
    private static Map<Integer,String> map = new HashMap<Integer,String>();
    @BeforeClass
    public static void init(){
        map.put(1,"Java");
        map.put(2,"PHP");
        map.put(3,"C++");
        map.put(4,"C#");
    }
    @Test
    public void raversalOne(){
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        for(Map.Entry<Integer,String> entry:set){
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }
    }
    @Test
    public void raversalTwo(){
        Set<Integer>keys = map.keySet();
        for(Integer key : keys){
            System.out.println("key:"+key+" value:"+map.get(key));
        }
    }
    @Test
    public void raversalThree(){
        Set<Integer>keys = map.keySet();
        for(Integer key : keys){
            System.out.println("key:"+key);
        }
        List<String> values = new ArrayList<String>(map.values());
        for(String value:values){
            System.out.println("value:"+value);
        }
    }
    @Test
    public void raversalFour(){
        Iterator<Map.Entry<Integer,String>>iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }
    }
}
