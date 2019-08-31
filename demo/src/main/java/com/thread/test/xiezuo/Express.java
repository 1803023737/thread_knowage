package com.thread.test.xiezuo;

public class Express {

    public final static String CITY="shanghai";
    private int km;
    private String site;

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public Express() {
    }

    public synchronized void waitSite() {
        while (CITY .equals(this.site)){
            try {
                System.out.println("check site thread is waiting! thread is "+Thread.currentThread().getId());
                wait();
                System.out.println("check site thread is "+Thread.currentThread().getId()+" is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this site is:"+site+",i will chenge db");
    }
    /**
     * 变化公里数  通知处于wait状态的需要处理km线程的进行业务处理
     * @return
     */
    public synchronized void changeKm() {
        this.km=101;
        notifyAll();
//        notify();
    }


    public synchronized void waitKm() {
        while (this.km<=100){
            try {
                System.out.println("check km thread is waiting! thread is "+Thread.currentThread().getId());
                wait();
                System.out.println("check km thread is "+Thread.currentThread().getId()+" is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this km is:"+km+",i will chenge db");
    }
    /**
     *
     * @return
     */
    public void changeSite() {
        this.site="beijing";
        //notifyAll();
        notify();
    }






}
