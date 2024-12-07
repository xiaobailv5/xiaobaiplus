package com.lv.xiaobaiplus.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: xiaobaiPlus
 * @ClassName HongBao2
 * @description: 发红包
 * @author: gxjh
 * @create: 2024-12-07 16:04
 * @Version 1.0
 **/
public class HongBao2 {


    public static void main(String[] args) {

        List<BigDecimal> list = new ArrayList();

        double money = 100.0;
        double minMoney = 1.0;
        int num = 10;

        double count = 0;
        while(num > 0){

            if (num == 1) {
               double money3 = Math.round(money * 100)/100;
               System.out.println(money3);
               count = count + money3;
               num--;
               break;
            }
            double maxMoney = money*3/10;

            Random random = new Random();
            double money2 = maxMoney*random.nextDouble();

            money2 = money2>minMoney?money2 : minMoney;

            money2 = Math.floor(money2*100) /100;
            count += money2;
            System.out.println(money2);
            num--;
            money = money-money2;

        }

        System.out.println(count);


    }
}