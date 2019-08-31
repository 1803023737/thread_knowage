package com.limit.xianliu.lingpaitong;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class MyRateLimiter implements AutoCloseable{

    //三个组件
    //信号量
    private Semaphore sem;
    //限制  一秒多少次
    private int limit;
    //定时器
    private Timer timer;

    public MyRateLimiter(int limit) {
        //传参  限制次数
        this.limit = limit;
        this.sem=new Semaphore(limit);
        timer=new Timer();

        //放入令牌的时间间隔
        long period=1000L/limit;
        //通过定时器  定时放入令牌
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //令牌数是否小于限制输
                if (sem.availablePermits()<limit){
                    sem.release();
                }
            }
        },period,period);
    }

    public void acquire() throws InterruptedException{
        this.sem.acquire();
    }

    public boolean tryAcquire(){
        return sem.tryAcquire();
    }

    public int availablePermits(){
        return this.sem.availablePermits();
    }

    @Override
    public void close() throws Exception {
        this.timer.cancel();
    }
}
