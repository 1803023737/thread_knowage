package com.thread.test.sync2;

public class Test {

    public static void main(String[] args) {
        Account account = new Account("100", 1000);
        new DrawThread("小王",account,800).start();
        new DrawThread("小林",account,800).start();

    }

}
