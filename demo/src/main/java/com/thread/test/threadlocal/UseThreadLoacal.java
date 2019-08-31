package com.thread.test.threadlocal;

/**
 * 每个线程只使用自己当前线程的副本   每个线程互不干扰 线程变量
 */
public class UseThreadLoacal {

     static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
         @Override
         protected Integer initialValue() {
             return 1;
         }
     };


     public void StartThreadArray(){
         Thread[] threads = new Thread[3];
         for (int i=0; i<threads.length;i++){
             threads[i]=new Thread(new TestThread(i));
         }
         for (int i=0; i<threads.length;i++){
             threads[i].start();
         }
     }

     public static class TestThread implements Runnable{

         int a=0;

         public TestThread(int a) {
             this.a = a;
         }

         @Override
         public void run() {
             Integer s=threadLocal.get();
             System.out.println(Thread.currentThread().getName()+",当前变量值："+s);
             s=s+a;
             threadLocal.set(s);
             System.out.println(Thread.currentThread().getName()+",当前变量值："+s);
         }
     }

    public static void main(String[] args) {
         UseThreadLoacal test=new UseThreadLoacal();
         test.StartThreadArray();
    }

}
