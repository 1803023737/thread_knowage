package com.thread.test.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DBPoolTest {

    //10个连接数
    static DBPool dbPool=new DBPool(10);
    //控制main线程等待所有的worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {

        //线程数量
        int threadCount=50;

        end =new CountDownLatch(threadCount);
        //每个线程的操作次数
        int count=20;
        //计数器  统计可以拿到连接的线程
        AtomicInteger got =new AtomicInteger();
        //计数器  统计吗没有拿到连接的线程
        AtomicInteger notgot =new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notgot), "worker_" + i);
            thread.start();
        }
        //mian线程在此处等待
        end.await();
        System.out.println("总共尝试了："+(threadCount*count));
        System.out.println("拿到连接数目："+got);
        System.out.println("没拿到连接数目："+notgot);

    }

    static class Worker implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notgot;

        public Worker(int count, AtomicInteger got, AtomicInteger notgot) {
            this.count = count;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {

            while (count>0){
                try {
                    //1秒钟之内拿不到   则提示连接超时
                    Connection connection = dbPool.fetchConnection(1000);
                    if (connection!=null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            dbPool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notgot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+"，等待超时！");
                    }
                }catch (Exception e){
                   e.printStackTrace();
                }finally {
                   count--;
                }
            }
            end.countDown();
        }
    }

}
