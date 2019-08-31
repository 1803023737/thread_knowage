package com.thread.test.forkjoin.sum;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int arr[]=new int[100];
        Random random=new Random();
        int total=0;
        long begin1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            int temp =random.nextInt(20);
            total+=(arr[i]=temp);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("耗时："+(end1-begin1));
        System.out.println("数组求和"+total);

        //线程分任务求和
        long begin2 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        SumTask sumTask = new SumTask(arr, 0, arr.length);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(sumTask);
        long end2 = System.currentTimeMillis();
        System.out.println("耗时："+(end2-begin2));
        System.out.println(submit.get());
    }

}
