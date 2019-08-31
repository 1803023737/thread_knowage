package com.thread.test.lock;

public class Test {

    public static void main(String[] args) {

        Account account=new Account("100",1000);
        new AccountThread("小王1",account,800).start();
        new AccountThread("小王2",account,800).start();


    }


}
