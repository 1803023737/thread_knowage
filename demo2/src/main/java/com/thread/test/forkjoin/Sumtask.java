package com.thread.test.forkjoin;

import java.util.concurrent.RecursiveTask;

public class Sumtask extends RecursiveTask<Integer> {

    private final static int THRESHOLD=MakeArray.ArrayLength/10;
    //需要计算的数组
    private int [] src;
    //数组的起始计算位置
    private int fromIndex;
    //数组的终止计算位置
    private int toIndex;

    public Sumtask(int[] src, int fromIndex, int toIndex) {
        this.src = src;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    /**
     * 多线程下统计数组    当不满足条件下进行拆分，满足的条件下进行计算
     * @return
     */
    @Override
    protected Integer compute() {
        if(toIndex-fromIndex<=THRESHOLD){
            int count=0;
            for (int i = fromIndex; i <=toIndex; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count+=src[i];
            }
            return count;
        }else{
            int middle=(fromIndex+toIndex)/2;
            //子任务
            Sumtask left=new Sumtask(src,fromIndex,middle);
            Sumtask right=new Sumtask(src,middle+1,toIndex);
            invokeAll(left,right);
            return left.join()+right.join();
        }
    }
}
