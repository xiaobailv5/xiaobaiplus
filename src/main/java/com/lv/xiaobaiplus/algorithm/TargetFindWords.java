package com.lv.xiaobaiplus.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @program: xiaobaiPlus
 * @ClassName TargetFindWords
 * @description:  给定一个字符串列表 words 和一个目标字符串 target;找出所有在 words 中出现过的子串(连续的字符序列〉，这些子串同时也在 target 中存在。
 *                返回这些子串中最长的一个。如果有多个长度相同的最长子串，则返回它们中字典序最小的那个。如果没有任何符合条件的子串，则返回空字符串""。
 *                words =["abcd","defg","defga"],target ="abcdefg"
 * @author: gxjh
 * @create: 2024-11-27 16:57
 * @Version 1.0
 **/
public class TargetFindWords {


    public static void main(String[] args) {

        String[] words = {"abcd","defg","defga"};
        String target = "abcdefg";
        System.out.println(findLongestWord(target, words));
    }

    private static String findLongestWord(String target, String[] words) {


        String reStr = "";

        for(String word : words) {
            if(target.contains(word)) {
                //1 前面长   0 等于   -1 后面长
                int l = Integer.compare(reStr.length(),word.length());
                if( l< 0) {
                    reStr = word;
                }else if (l == 0) {
                    // 长度相等，比较字典序
                    int l2 = reStr.compareTo(word);
                    if(l2 > 0) {
                        reStr = word;
                    }
                }

            }

        }
        return reStr;



        /*String maxStr = "";
        //没有任何符合条件的子串，则返回空字符串""
        if (list.size() == 0) {
            return maxStr;
        }
        //排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int l1 = Integer.compare(o1.length(),o2.length());
                if(l1 != 0 ){
                    return l1;
                }
                return o1.compareTo(o2);

            }
        });

        return list.get(0);*/
    }
}