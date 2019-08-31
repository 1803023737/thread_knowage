package com.thread.test.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class SumArray {

    public static void main(String[] args) {
        //线程池
        ForkJoinPool pool=new ForkJoinPool();
        int [] src=MakeArray.makeArray();
        long start = System.currentTimeMillis();
        //总任务
        Sumtask sumtask = new Sumtask(src, 0, src.length - 1);
        //线程池中加任务  同步用法  必须这句代码执行完才能执行下面的代码   当时间很久   主线程就被阻塞了
        Integer invoke = pool.invoke(sumtask);
        System.out.println("task is running!");
        long end = System.currentTimeMillis();
        Integer count1 = sumtask.join();
        System.out.println("the count is:"+sumtask.join()+" spend time:"+(end-start)+"ms");

        //单线程中跑任务 测试计算的值是否一致
        long start2 = System.currentTimeMillis();
        Integer count2 = SumNormal.SumNormalTask(src);
        long end2= System.currentTimeMillis();
        System.out.println("the count is:"+count2+" spend time:"+(end2-start2)+"ms");
    }
}
