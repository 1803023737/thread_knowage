package com.limit.xianliu.limit;

import java.util.Timer;
import java.util.TimerTask;

public class MyLimiter {

    private Integer limit;

    private Integer userful;
    //延迟秒书
    private Long delay=1000L;

    private Integer interval=200;

    public MyLimiter(int limit) {
        this.limit = limit;
        this.userful = limit;
        //增量=总的阈值/速度
        Integer increment=limit/(1000/interval);
        System.out.println("increment:"+increment);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //定时增加
                if (userful<limit){
                    System.out.println("增加。。。"+increment+",当前时间："+System.currentTimeMillis());
                    userful=userful+increment;
                }
            }
        },delay,interval);
    }

    /**
     * 获得流量空间
     * @param waste
     */
    public void acquire(Integer waste){
        userful=userful-waste;
    }

    /**
     * 是否有足够的空间提供给流量消耗
     * @param waste
     * @return
     */
    public boolean tryAcquire(Integer waste){
        boolean flag=false;
        if (userful>waste){
            flag=true;
        }
        return flag;
    }

    public Integer availableAmount(){
        return userful;
    }
}
