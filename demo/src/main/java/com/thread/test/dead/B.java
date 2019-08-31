package com.thread.test.dead;

public class B {

    public synchronized void beginA(A a){
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入beginA 方法！");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class A 的endA 方法！");
        a.endA();
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class A 的endA 方法结束！");
    }

    public synchronized void endB(){
        System.out.println("当前线程名称："+Thread.currentThread()+"，进入class A 的endA 方法！");
    }

}
