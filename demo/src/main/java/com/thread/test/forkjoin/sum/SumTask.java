package com.thread.test.forkjoin.sum;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private static final Integer THRESHOLD=25;
    private int start;
    private int end;
    private int [] arr;

    public SumTask(int [] arr,int start, int end) {
        this.arr=arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
         int num=0;
        if (end-start<THRESHOLD){
            for (int i = start; i < end; i++) {
                num+=arr[i];
            }
            System.out.println(Thread.currentThread()+"，获得的求和为："+num);
            return num;
        }else{

            int middle=(start+end)/2;
            SumTask left = new SumTask(arr, start, middle);
            SumTask right = new SumTask(arr, middle, end);
            left.fork();
            right.fork();
            return left.join()+right.join();
        }

    }
}
