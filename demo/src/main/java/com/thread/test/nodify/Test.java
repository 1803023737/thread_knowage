package com.thread.test.nodify;

public class Test {

    public static void main(String[] args) {
        BankAccount account=new BankAccount("123456",0);
        //存款
        new Thread(new AccountDepositThread("存款线程",account,800)).start();
        //取款
        new Thread(new AccountDrawThread("取款线程",account,800)).start();
    }

}
