package com.limit.xianliu.limit;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TestUse {

    //10M阈值
    private MyLimiter myLimiter=new MyLimiter(1000);

    public static void main(String[] args) {

        CountDownLatch countDownLatch=new CountDownLatch(10);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);
        TestUse testUse=new TestUse();
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("当前线程为："+Thread.currentThread().getName()+"正在等待！");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    String result = testUse.process();
                    System.out.println(result);
                    //每个线程执行完  主线程才能继续执行
                    countDownLatch.countDown();
                }
            }).start();
        }
        //休眠1秒
        try {
            countDownLatch.await();
            System.out.println("休眠1s。。。qian:"+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("休眠1s。。。"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  "+testUse.process());
    }

    //处理
    public synchronized String process(){
        if (myLimiter.tryAcquire(150)){
            System.out.println( "当前线程为："+Thread.currentThread().getName()+",进入。。。");
            myLimiter.acquire(150);
           return "当前线程为："+Thread.currentThread().getName()+",剩余："+myLimiter.availableAmount()+",执行时间";
        }else{
            return "当前线程为："+Thread.currentThread().getName()+",可用流量不够,可用流量只有为："+myLimiter.availableAmount();
        }
    }

}
