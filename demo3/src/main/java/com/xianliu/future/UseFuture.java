package com.xianliu.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseFuture {

     public static class UseCallable implements Callable<Integer>{

         private int num;

         @Override
         public Integer call() throws Exception {
             System.out.println("子线程开始计算。。。");

             Thread.sleep(2000);
             for (int i = 0; i < 5000; i++) {
                 num+=i;
             }
             System.out.println("子线程计算完成。。。结果为："+num);
             return num;
         }
     }

    public static void main(String[] args) {

        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        Random random = new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //随机数决定
        if (random.nextBoolean()){
            try {
                System.out.println("获得结果！！！"+futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("终止任务！！！");
            //会对阻塞方法进行抛出异常！！！相当于interupt
            futureTask.cancel(true);
        }

    }

}
