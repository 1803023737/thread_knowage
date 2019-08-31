package com.thread.test.dead;



public class DeadLock implements Runnable {


    A a=new A();
    B b=new B();

    public void init(){
        System.out.println("主线程！");
        a.beginA(b);
        System.out.println("主线程完毕！");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("第二条线程");
        b.beginA(a);
        System.out.println("第二条线程完毕！");
    }
}
