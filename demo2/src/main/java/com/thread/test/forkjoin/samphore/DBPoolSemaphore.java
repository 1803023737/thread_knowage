package com.thread.test.forkjoin.samphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class DBPoolSemaphore {

    private final static int PoolSize=10;
    //useful可用数据库连接
    private final Semaphore useful;
    private final Semaphore useless;

    public DBPoolSemaphore() {
        useful = new Semaphore(PoolSize);
        useless=new Semaphore(0);
    }

    //存放数据库连接
    private static LinkedList<Connection> pool=new LinkedList<>();
    //初始化池子
    static {
        for (int i=0;i<PoolSize;i++){
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public void returnConnection(Connection connection) throws InterruptedException {
        if (connection!=null){
            System.out.println("当前有"+useful.getQueueLength()+"线程等待数据库连接！"+"可用连接数："+useful.availablePermits());
            useless.acquire();
            //归还连接
            synchronized (pool){
                pool.addLast(connection);
            }
            //
            useful.release();
        }
    }

    public Connection takeConnection() throws InterruptedException {
        //拿许可证   可用减少一个
//        System.out.println("当前线程是否立马拿到许可！"+Thread.currentThread().getId()+":"+useful.tryAcquire());
        //阻塞等待的方法  一直等待
        useful.acquire();
        Connection conn;
        synchronized (pool){
            conn=pool.removeLast();
        }
        //无用的连接减少一个
        useless.release();
        return conn;
    }

}
