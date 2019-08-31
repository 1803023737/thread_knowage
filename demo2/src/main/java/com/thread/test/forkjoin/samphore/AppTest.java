package com.thread.test.forkjoin.samphore;

import java.sql.Connection;
import java.util.Random;

public class AppTest {

    private static DBPoolSemaphore dbpool=new DBPoolSemaphore();

    public static void main(String[] args) {
          for (int i=0;i<50;i++){
              BusiThread busiThread = new BusiThread();
              busiThread.start();
          }

    }

    public static class BusiThread extends Thread{
        @Override
        public void run() {

            Random random=new Random();
            long start=System.currentTimeMillis();
            try {
                Connection connection = dbpool.takeConnection();
                if (connection!=null){
                    System.out.println("Thread_"+Thread.currentThread().getId()+"_获得数据库连接共耗时["+(System.currentTimeMillis()-start)+"]ms");
                    //模拟数据库连接操作时间
                    Thread.sleep(100+random.nextInt(100));
                    System.out.println("数据库查询完，准备归还连接");
                    dbpool.returnConnection(connection);
                }else{
                    System.out.println("Thread_"+Thread.currentThread().getId()+" 没拿到连接。。。");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
