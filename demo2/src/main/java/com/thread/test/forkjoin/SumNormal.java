package com.thread.test.forkjoin;

public class SumNormal {

    public static void main(String[] args) {

       /* int count=0;
        int [] array=MakeArray.makeArray();
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count+=array[i];
        }
        System.out.println("求和大小："+count);
        long end = System.currentTimeMillis();
        System.out.println("总耗时长："+(end-start)+"s");*/
    }

    public static Integer SumNormalTask(int [] array){
        int count=0;
        for (int i = 0; i < array.length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count+=array[i];
        }
        return count;
    }

}
