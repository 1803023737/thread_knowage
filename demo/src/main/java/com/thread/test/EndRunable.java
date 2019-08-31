package com.thread.test;

public class EndRunable {

    private static class UseRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("执行线程");
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("当前线程名称："+Thread.currentThread().getName());
            }
            System.out.println("线程中断！");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        UseRunable useRunable=new UseRunable();
        Thread thread = new Thread(useRunable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }


}
