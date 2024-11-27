package com.lv.xiaobaiplus.algorithm;


/**
 * @program: xiaobaiPlus
 * @ClassName GetKMax
 * @description: 给一个数据 获取第k大的元素
 * @author: gxjh
 * @create: 2024-11-27 18:43
 * @Version 1.0
 **/
public class GetKMax {

    public static void main(String[] args) {
        int[] arr = {7, 6, 3, 4, 5, 2, 1, 8, 9, 10};
        int k = 5;
        int kMax = getKMax(arr, k);
        System.out.println(kMax);
    }

    private static int getKMax(int[] arr, int k) {
        //冒泡排序
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        //工具排序
//        Arrays.sort(arr);

        return arr[k];
    }

}