package com.thread.test.nodify;

public class AccountDrawThread implements    Runnable {

    private BankAccount account;
    private double draw;

    public AccountDrawThread(String name, BankAccount account, double draw){
       Thread.currentThread().setName(name);
       this.account=account;
       this.draw=draw;
    }

    @Override
    public void run() {
        //取款10次
        for (int i = 0; i < 3; i++) {
            System.out.println("线程："+Thread.currentThread().getName()+":准备取款，第"+i+"次取款。。。");
             account.draw(draw);
        }

    }

}
