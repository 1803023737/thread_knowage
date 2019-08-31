package com.thread.test.sync2;

public class DrawThread extends Thread {

    private Account account;
    private double drawAmount;
    public DrawThread(String name, Account account, double drawAmount){
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }

    @Override
    public void run() {
        account.drawMoney(drawAmount);
    }
}
