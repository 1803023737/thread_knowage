package com.thread.test.nodify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行账号
 */
public class BankAccount {

    private String num;
    private double money;

    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public BankAccount(String num, double money) {
        this.num = num;
        this.money = money;
    }

    //是否有存款  true存款标志  false 取款标志
    private boolean flag=false;

    //取钱
    public void draw(double draw){

        lock.lock();
        try{
            //没存款
            if (!flag){
                try {
                    System.out.println(Thread.currentThread()+"线程开始取款等待");
                    condition.await();
                    System.out.println(Thread.currentThread()+"线程开始取款等待结束，已经有存款进入，被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(Thread.currentThread()+"线程开始取款："+draw);
                money=money-draw;
                flag=false;
                condition.signalAll();
                System.out.println(Thread.currentThread()+"线程取款后，账户余额为："+money);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //存款
    public  void deposit(double depositamount){

        lock.lock();
        try {
            //有存款
            if (flag){
                try {
                    System.out.println("================"+Thread.currentThread()+"线程开始存款等待");
                    condition.await();
                    System.out.println("================"+Thread.currentThread()+"线程开始存款等待结束，已经有取款取出，被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("================"+Thread.currentThread()+"线程开始存款："+depositamount);
                money=money+depositamount;
                flag=true;
                condition.signalAll();
                System.out.println("================"+Thread.currentThread()+"线程存款后，账户余额为："+money);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }


}
