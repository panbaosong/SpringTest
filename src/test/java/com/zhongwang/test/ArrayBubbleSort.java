package com.zhongwang.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/19 15:23
 * @Description: 数组冒泡排序
 */
public class ArrayBubbleSort {
    private static int [] arr = new int[10];

    @BeforeClass
    public static void init(){
        Random r = new Random();
        for(int i = 0;i<arr.length;i++){
            arr[i] = r.nextInt(100);
        }
    }
    /**
    * @Description: 冒泡排序升序
    * @Param: []
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/19
    */
    @Test
    public void bubbleSort(){
        for(int i = 0 ; i < arr.length-1 ; i++){
            for(int j = 0 ; j < arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
