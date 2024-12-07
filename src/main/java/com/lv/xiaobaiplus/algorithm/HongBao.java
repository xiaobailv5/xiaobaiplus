package com.lv.xiaobaiplus.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: xiaobaiPlus
 * @ClassName HongBao
 * @description: 发红包  100元 10个红包   要求每个红包数量大于1元但是要小于整个红包的30%.
 * @author: gxjh
 * @create: 2024-12-07 10:02
 * @Version 1.0
 **/
public class HongBao {

    public static void main(String[] args) {

        //红包个数
        int num = 10;
        //总红包金额
        double money = 100.0;

        //存储10个红包
        List<BigDecimal> resultList = new ArrayList();

        while(num > 0){
            double money2 = getRandomMoney(money,num,1);
            num--;
            money = money-money2;
            System.out.println(money2);
            resultList.add(new BigDecimal(money2 + ""));
        }
        double count = 0.0;
        for(int i=0;i<resultList.size();i++) {
            count += resultList.get(i).doubleValue();
        }
        System.out.println(count);
    }

    /**
     * 获取红包金额
     * @param remainMoney 红包金额总额
     * @param remainSize 红包个数
     * @param minMoney 最少红包金额
     * @return
     */
    public static double getRandomMoney(double remainMoney, int remainSize, int minMoney) {

        if(remainSize == 1) {
            remainSize --;
            return ((double)Math.round(remainMoney * 100)) /100;
        }

        Random random = new Random();
        //最大是红包的30%
        double max = remainMoney/10*3;
        //获取红包金额
        double money = max * random.nextDouble();
        //如果金额小于1 则为1
        money = money < minMoney ? minMoney : money;
        //向下取
        money = Math.floor(money * 100)/100;


        return money;

    }






}