package com.thread.test.forkjoin.bingfautil;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 让一组线程到达某个屏障  被阻塞  到组内最后一个线程到达屏障  屏障开放  所有被阻塞的线程 会继续运行
 */
public class UseCyclicBarrier {

    //指定工作线程5
    //private static CyclicBarrier barrier=new CyclicBarrier(5);
    //五个线程达到后  执行collectThread
    private static CyclicBarrier barrier=new CyclicBarrier(5,new CollectThread());

    //存放子线程工作结果的容器
    private static ConcurrentHashMap<String,Long> resultMap=new ConcurrentHashMap();

    //业务线程
    private static class SubThread implements Runnable{

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId()+"",id);
            Random random=new Random();
            //随机数为真
            if (random.nextBoolean()){
                try {
                    Thread.sleep(1000+id);
                    System.out.println("thread_"+id+"...do something!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("thread_"+id+"...is  await!");
                //等待   所有工作线程  到达这边才能继续往下走
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000+id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread_"+id+"...do business!");
        }
    }

    //负责屏障开放后的工作
    private static class CollectThread implements Runnable{

        @Override
        public void run() {
            StringBuffer result=new StringBuffer();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("["+workResult.getValue()+"]");
            }
            System.out.println(" the result="+result);
            System.out.println(" do other business!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }

    }



}
