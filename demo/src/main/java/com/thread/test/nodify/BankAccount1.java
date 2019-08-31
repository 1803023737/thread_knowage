package com.thread.test.nodify;

/**
 * 银行账号
 */
public class BankAccount1 {

    private String num;
    private double money;

    public BankAccount1(String num, double money) {
        this.num = num;
        this.money = money;
    }

    //是否有存款  true存款标志  false 取款标志
    private boolean flag=false;

    //取钱
    public synchronized void draw(double draw){

        //没存款
          if (!flag){
              try {
                  System.out.println(Thread.currentThread()+"线程开始取款等待");
                  wait();
                  System.out.println(Thread.currentThread()+"线程开始取款等待结束，已经有存款进入，被唤醒");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }else{
              System.out.println(Thread.currentThread()+"线程开始取款："+draw);
              money=money-draw;
              flag=false;
              notifyAll();
              System.out.println(Thread.currentThread()+"线程取款后，账户余额为："+money);
          }
    }

    //存款
    public synchronized void deposit(double depositamount){
      //有存款
        if (flag){
          try {
              System.out.println("================"+Thread.currentThread()+"线程开始存款等待");
              wait();
              System.out.println("================"+Thread.currentThread()+"线程开始存款等待结束，已经有取款取出，被唤醒");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }else{
            System.out.println("================"+Thread.currentThread()+"线程开始存款："+depositamount);
            money=money+depositamount;
            flag=true;
            notifyAll();
            System.out.println("================"+Thread.currentThread()+"线程存款后，账户余额为："+money);
      }

    }


}
