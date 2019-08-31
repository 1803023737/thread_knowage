package com.thread.test;

public class EndThread2 {

    public static void main(String[] args) throws InterruptedException {

        UserThread endThread = new UserThread("endThread");
        endThread.start();
        Thread.sleep(500);
        //中断线程   将线程的标志位设为true 协作式并不代表一定立马关闭   只是将线程标识位修改，具体是否中断  需要线程自行控制
        endThread.interrupt();

    }

    private static class UserThread extends Thread{

        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //线程没中断
            int i=0;
            while (!isInterrupted()){// 只是将线程标识位修改，具体是否中断  需要线程自行控制
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //当抛出InterruptedException后，线程的标志位变成false，如果需要中断，则需要加上interrupt()方法
                    System.out.println(Thread.currentThread().getName()+",该线程标志位："+isInterrupted());
                   interrupt();
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                System.out.println("当前线程名称："+i+"====="+name);
                i++;
            }
            System.out.println("线程中断！！！");
        }
    }
}
