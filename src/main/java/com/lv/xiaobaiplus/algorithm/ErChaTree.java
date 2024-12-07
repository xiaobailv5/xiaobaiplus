package com.lv.xiaobaiplus.algorithm;

/**
 * @program: xiaobaiPlus
 * @ClassName ErChaTree
 * @description: 反转二叉树
 * @author: gxjh
 * @create: 2024-12-07 09:45
 * @Version 1.0
 **/
public class ErChaTree {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val=val;
        }
    }




    //递归
    public TreeNode InvertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left=root.right;
        root.right = temp;

        return root;
    }

    //冒泡排序
    public static void main(String[] args) {

        int[] arr = {2,4,3,8,5,7};
        for (int i=0; i<arr.length;i++) {
            for(int j= 0; j<arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(arr);

    }
}