package com.thread.test.lock;

public class AccountThread extends Thread {

    private Account account;
    private double draw;
    public AccountThread(String name,Account account,double draw){
        super(name);
        this.account=account;
        this.draw=draw;
    }

    @Override
    public void run() {
         account.drawBalance(draw);

    }
}
