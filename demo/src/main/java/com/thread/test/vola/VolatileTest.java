package com.thread.test.vola;

public class VolatileTest {

    public static void main(String[] args) {

         VolatileVar volatileVar=new VolatileVar();
         Thread t1=new Thread(volatileVar);
         Thread t2=new Thread(volatileVar);
         Thread t3=new Thread(volatileVar);
         Thread t4=new Thread(volatileVar);
         t1.start();
         t2.start();
         t3.start();
         t4.start();

    }

    //volatile 不安全操作  volatile修饰下、对象最主内存中，每个线程拿到一个副本  无法保证线程安全
    //volatile 试用场景  一个线程写  多个线程读  只能够确保可见性
    private static class VolatileVar implements Runnable{
        private volatile int a=0;

        @Override
        public void run() {
            Thread thread=Thread.currentThread();
            a=a+1;
            System.out.println("当前线程名称："+thread.getName()+"当前变量值是："+a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a=a+1;
            System.out.println("当前线程名称："+thread.getName()+"当前变量值是："+a);
        }
    }

}
