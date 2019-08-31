package com.thread.test.forkjoin.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseFuture {

    /**
     * FutureTask  将caLLABLE包装成runnable  执行
     * FutureTask 实现runnable接口  future接口  构造器入参callable
     * callable  可以有返回值  可以抛出异常  与runnable的区别
     */
    private static class UseCallable implements Callable<Integer>{

        private int num;

        @Override
        public Integer call() throws Exception {
            System.out.println("callable子线程开始计算");
            Thread.sleep(2000);
            for (int i=0;i<5000;i++){
                num+=i;
            }
            System.out.println("callable子线程计算结束");
            return num ;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseCallable useCallable=new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        new Thread(futureTask).start();

        //主线程继续做事
        Thread.sleep(1000);
        Random random=new Random();
        if (random.nextBoolean()){
            //futureTask.get()   阻塞方法  可以设置时间限制   到达一定时间  没获得结果跑出异常
            System.out.println("get usecallable result="+futureTask.get());
        }else{
            System.out.println("中断计算");
            //取消任务
            futureTask.cancel(true);
        }
    }


}
