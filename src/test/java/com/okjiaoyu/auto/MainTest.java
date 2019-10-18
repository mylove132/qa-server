package com.okjiaoyu.auto;

import java.io.File;
import java.util.Random;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-08:10:14
 * Modify date: 2019-10-08:10:14
 */
public class MainTest {


    public static void main(String[] args) {

        File file = new File("");
        System.out.println(System.getProperty("user.dir"));
//        final int[] arr = new int[]{1,4,2,7,3,9,10,2,5,0};
//        mpSort(arr);
//        qucitSort(arr,0,arr.length-1);
//        for (int i=0;i<arr.length;i++){
//            System.out.print(arr[i]+" ");
//        }
//        int index = search(arr,9);
//        System.out.println(index);
    }

    public static void qucitSort(int[] array,int low,int high){
        if (low < high){
            int index = getIndex(array,low,high);
            qucitSort(array,0,index-1);
            qucitSort(array,index+1,high);
        }
    }


    private static int getIndex(int[] array, int low, int high) {
        int tmp = array[low];
        while (low<high){
            while (low<high&&array[high] >= tmp){
                high--;
            }
            array[low] = array[high];
            while (low<high&&array[low] <= tmp){
                low++;
            }
            array[high]=array[low];
        }
        array[low] = tmp;
        return low;
    }

    public static void mpSort(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int k=0;k<arr.length-i-1;k++){
                int tmp = 0;
                if (arr[k]>arr[k+1]){
                    tmp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = tmp;
                }
            }
        }

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void swap(int[] arr,int low,int high){
        int tmp = 0;
        Random r = new Random();
        int k = r.nextInt(10)%(high-low+1)+low;
        tmp = arr[low];
        arr[low] = arr[k];
        arr[k] = tmp;
    }

    static int search(int[] arr,int search){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int middle = (low+high)/2;
            if (arr[middle] == search){
                return middle;
            }
            if (arr[middle]<search){
                low = middle+1;
            }else {
                high = middle-1;
            }
        }
        return -1;

    }
}
