package com.limit.xianliu.lingpaitong;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TestQuery {

    //平滑限流请求数
    private MyRateLimiter myRateLimiter=new MyRateLimiter(5);

    public static void main(String[] args) {

        CountDownLatch countDownLatch=new CountDownLatch(10);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);
        TestQuery testQuery = new TestQuery();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            new Thread(()-> {
                try {
                    System.out.println("============");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"  "+testQuery.doQuery3());
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //休眠1秒
        try {
            Thread.sleep(1000);
            System.out.println("休眠1s。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  "+new TestQuery().doQuery3());
    }

    public synchronized String doQuery3(){
        //是否还有令牌
        if (myRateLimiter.tryAcquire()){
            return "您的年度消费统计！"+System.currentTimeMillis()/1000+"剩余令牌："+myRateLimiter.availablePermits();
        }else {
            return "系统正忙，请重试！当前时间："+System.currentTimeMillis()/1000;
        }
    }
}
