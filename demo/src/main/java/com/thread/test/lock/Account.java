package com.thread.test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    //加锁
    private final ReentrantLock lock = new ReentrantLock();

    private String recode;
    private double balance;

    public Account(String recode, double balance) {
        this.recode = recode;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void drawBalance(double draw) {
        lock.lock();
        try {
            //取款小于等于余额
           if (draw<=balance){
               System.out.println("当前线程："+Thread.currentThread().getName()+",开始取款");
               Thread.sleep(1000);
               balance=balance-draw;
               System.out.println("当前线程："+Thread.currentThread().getName()+",账户余额："+balance);
           }else{
               System.out.println("当前线程："+Thread.currentThread().getName()+",无法取款，余额不足");
           }
        } catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }
}
