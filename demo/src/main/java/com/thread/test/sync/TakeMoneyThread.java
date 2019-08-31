package com.thread.test.sync;

public class TakeMoneyThread extends  Thread{

    private Account account;
    private double drawAmount;
    public TakeMoneyThread(String name,Account account,double drawAmount){
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }

    @Override
    public void run() {
        //当余额大于取钱的数目时候  才能取钱
        if (account.getBalance()>drawAmount){
            System.out.println(this.getName()+":取钱成功！"+drawAmount);
   /*         try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            account.setBalance(account.getBalance()-drawAmount);
            System.out.println(this.getName()+":账户余额："+account.getBalance());
        }else{
            System.out.println(this.getName()+":资金不足！");
        }
    }
}
