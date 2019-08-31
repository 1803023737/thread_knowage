package com.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * java中线程是协作式的，不是强占式的   原因是要线程做清理操作
 * interrupt  是将中断标志位是否为true
 *
 */
public class NewThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程。。。。。。。。。。。1");
        UseRun useRun=new UseRun();
        Thread thread1 = new Thread(useRun);
        thread1.start();
        System.out.println("主线程。。。。。。。。。。。2");

        UseCall useCall=new UseCall();
        //FutureTask  实现了runable接口
        FutureTask<String> stringFutureTask = new FutureTask<String>(useCall);
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        /**
         * stringFutureTask.get()  是阻塞方法   会等到callable执行完成
         */
        System.out.println(stringFutureTask.get());
    }

    private static class UseRun implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runable 方法！！！");
        }
    }

    /**
     * callable 是可以拿到返回值
     */
    private static class UseCall implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("UseCall 方法！！！");
            return "abc";
        }
    }


}
