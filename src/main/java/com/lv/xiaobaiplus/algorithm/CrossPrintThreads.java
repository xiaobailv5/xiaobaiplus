package com.lv.xiaobaiplus.algorithm;

/**
 * @program: xiaobaiPlus
 * @ClassName CrossPrintThreads
 * @description: 启两个线程  交叉打印1到100 奇数和偶数
 * @author: gxjh
 * @create: 2024-12-07 19:37
 * @Version 1.0
 **/
public class CrossPrintThreads {

    private static final Object lock = new Object();
    private static boolean isThread1Run = true;//线程1是否执行标识 true执行 false 不执行
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for(int i = 1; i<=100; i += 2) {
                synchronized(lock) {
                    while(!isThread1Run) {
                        try {
                            lock.wait();
                        }catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(i);
                    isThread1Run = false;
                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = 2; i<=100;i+=2){
                synchronized(lock) {
                    while(isThread1Run){
                        try {
                            lock.wait();
                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(i);
                    isThread1Run = true;
                    lock.notify();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}