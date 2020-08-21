package com.zhongwang.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/20 10:07
 * @Description: 快速排序
 * 基本思想：快排使用的是分治法，先选一个数作为Pivot，Pivot把数组分割成一大一小左右两个部分，再使用相同
 * 的方法分别处理这两部分
 * 当数组分割到最后只剩一个元素的时候，这个元素（数组）就是已经排序好的
 * daily，放左边那堆也行，放右边那堆也行，甚至不动它都行，快排不是稳定排序
 * 选出Pivot，再把数组分割的过程叫作Partition，Partition的实现方法有很多种，Partition的效率是影响快排的重要因素
 * Pivot所在的位置正好是排好后的位置，这个特性也常常运用在“找数组第N大的数”中
 *
 */
public class ArrayQuickSort {
    private static int [] arr = new int[10];

    @BeforeClass
    public static void init(){

        Random r = new Random();
        for(int i= 0;i<arr.length;i++){
            arr[i] = r.nextInt(100);
        }
    }
    /**
    * @Description: 挖坑法
    * @Param: [数组, startIndex, endIndex]
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/20
    */
    public static void quickSort(int arr[],int startIndex,int endIndex){
        //递归结束条件
        if(startIndex>=endIndex){
            return;
        }
        //取第一个位置的元素作为基础元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        //坑的位置，初始等于pivot的位置
        int index= startIndex;

        //大循环在左右指针重合或交错时结束
        while(right>=left){
            //right指针从右向左进行比较
            while(right>=left){
                if (arr[right]<pivot){
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while ( right >= left ) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        quickSort(arr, startIndex, index - 1);
        quickSort(arr, index + 1, endIndex);
    }
    /**
    * @Description: 指针交换法
    * @Param: [arr, startIndex, endIndex]
    * @return: void
    * @Author: liulonganys
    * @Date: 2020/8/20
    */
    public void pointerQuickSort(int[]arr,int startIndex,int endIndex){
        //递归结束条件
        if(startIndex>=endIndex){
            return;
        }
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while( left != right) {
            //控制right指针比较并左移
            while(left<right && arr[right] > pivot){
                right--;
            }
            //控制right指针比较并右移
            while( left<right && arr[left] <= pivot) {
                left++;
            }
            //交换left和right指向的元素
            if(left<right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;
        // 得到基准元素位置
        int pivotIndex = left;
        // 根据基准元素，分成两部分递归排序
        pointerQuickSort(arr, startIndex, pivotIndex - 1);
        pointerQuickSort(arr, pivotIndex + 1, endIndex);
    }

    @Test
    public void  sys(){
        pointerQuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

}

