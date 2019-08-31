package com.thread.test.forkjoin.exchange;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchanger  只限制于用于两个线程数据交换
 * 阻塞方法   先打到exchange方法的方法先阻塞
 *
 */
public class UseExchange {

    //交换数据为set集合
    private static final Exchanger<Set<String>> exchange=new Exchanger<Set<String>>();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA=new HashSet<String>();
                try {
                    setA.add("1");
                    setA.add("2");
                    setA.add("3");
                    setA = UseExchange.exchange.exchange(setA);
                    System.out.println("setA:"+setA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB=new HashSet<String>();
                try {
                    setB.add("a");
                    setB.add("b");
                    setB.add("c");
                    setB = UseExchange.exchange.exchange(setB);
                    System.out.println("setB:"+setB);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
