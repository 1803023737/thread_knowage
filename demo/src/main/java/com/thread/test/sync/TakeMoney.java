package com.thread.test.sync;

//取钱
public class TakeMoney {

    public static void main(String[] args) {

       /* Account account = new Account("1", 1000);
        new TakeMoneyThread("取现1",account,800).start();
        new TakeMoneyThread("取现2",account,800).start();*/


        Account account = new Account("1", 1000);
        new TakeMoneyThread2("取现1",account,800).start();
        new TakeMoneyThread2("取现2",account,800).start();

    }

}
