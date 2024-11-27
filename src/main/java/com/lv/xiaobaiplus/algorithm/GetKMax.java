package com.lv.xiaobaiplus.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = arr[left];
            arr[left] = arr[right];
            arr[right] = mid;
            left++;
            right--;
        }
        //工具排序
        Arrays.sort(arr);

        return arr[k];
    }

}