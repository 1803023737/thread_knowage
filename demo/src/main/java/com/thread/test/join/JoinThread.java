package com.thread.test.join;

public class JoinThread extends Thread{

    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"===="+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //主线程
        System.out.println("主线程开始："+Thread.currentThread().getName());
        //新线程开启
        new JoinThread("新线程：").start();
        for (int i = 0; i < 100; i++) {
            if (i==20){
                JoinThread joinThread = new JoinThread("被join线程：" + i);
                joinThread.start();
                joinThread.join();
            }
            //主线程  被join  等待其他线程执行完 在执行
            System.out.println("主线程开始："+i);
        }


    }


}
