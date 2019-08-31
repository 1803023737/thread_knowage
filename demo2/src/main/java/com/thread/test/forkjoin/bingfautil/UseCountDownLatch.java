package com.thread.test.forkjoin.bingfautil;

import java.util.concurrent.CountDownLatch;

/**
 * 类说明  demo  五个初始化线程 6个扣除点
 *  扣除完毕  主线程和业务线程  才能自己的工作
 */
public class UseCountDownLatch {

    //6个扣除点   不代表6个县城
    static CountDownLatch latch=new CountDownLatch(6);


    //初始化线程
    private static class InitThread implements Runnable{

        @Override
        public void run() {
            System.out.println("thread_"+Thread.currentThread().getId()+" ready init work!");
            //初始化线程完成工作 点数减一
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("thread_"+Thread.currentThread().getId()+"...continue do its work!");
            }

        }
    }

    //业务线程
    private static class BusinessThread implements Runnable{

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("business_"+Thread.currentThread().getId()+" do business!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
         //初始化线程  两次  扣减两次
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread_"+Thread.currentThread().getId()+" ready init2 work ");
                latch.countDown();

                System.out.println("begin!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread_"+Thread.currentThread().getId()+"ready init work second");
                latch.countDown();
            }
        }).start();

        //业务线程
        new Thread(new BusinessThread()).start();

        //初始化线程
        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }

        latch.await();
        System.out.println("main thread is working!");

    }


}
