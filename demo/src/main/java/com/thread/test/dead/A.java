package com.thread.test.dead;

public class A {

    public synchronized void beginA(B b){
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入beginA 方法！");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class B 的endb 方法！");
        b.endB();
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class B 的endb 方法结束！");
    }

    public synchronized void endA(){
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class A 的endA 方法！");
    }

}
