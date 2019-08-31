package com.thread.test.sync2;

public class Account {

    private String code;
    private double balance;

    public Account(String code, double balance) {
        this.code = code;
        this.balance = balance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void drawMoney(double drawAmount) {
            //当余额大于取钱的数目时候  才能取钱
            if (getBalance()>drawAmount){
                System.out.println(Thread.currentThread().getName()+":取钱成功！"+drawAmount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance=balance-drawAmount;
                System.out.println(Thread.currentThread().getName()+":账户余额："+balance);
            }else{
                System.out.println(Thread.currentThread().getName()+":资金不足！");
            }
        }
}
