package com.thread.test;

public class EndThread {

    public static void main(String[] args) throws InterruptedException {

        UserThread endThread = new UserThread("endThread");
        endThread.start();
        Thread.sleep(1000);
        //中断线程   将线程的标志位设为true 协作式并不代表一定立马关闭
        endThread.interrupt();

    }

    private static class UserThread extends Thread{

        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //线程没中断
            while (!isInterrupted()){
                String name = Thread.currentThread().getName();
                System.out.println("当前线程名称："+name);
            }
            System.out.println("线程中断！！！");
        }
    }


}
