package com.thread.test.dead;

public class Test {

    public static void main(String[] args) {

        DeadLock deadLock=new DeadLock();
        new Thread(deadLock).start();

        deadLock.init();
    }

}
