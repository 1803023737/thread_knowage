package com.thread.test.pool;

import java.sql.Connection;
import java.util.LinkedList;

public class DBPool {

    private static LinkedList<Connection> pool=new LinkedList<Connection>();

    public DBPool(int initsize) {
        if (initsize>0){
            for (int i = 0; i < initsize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //mill  设定等待时长
   public Connection fetchConnection(long mills){
        synchronized (pool){
            //一直等待
            if (mills<0){
                while (pool.isEmpty()){
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //等待结束  则返回池子中的第一个连接
                return pool.removeFirst();
            }else{
               long overtime=System.currentTimeMillis()+mills;
               long remain=mills;
               //连接池为空  只能等待其他链接归还
               while (pool.isEmpty() && remain>0){
                   try {
                       //reamin是动态减少的
                       pool.wait(remain);
                       //等待剩余时间   等待时间不满足条件时跳出循环
                       remain=overtime-System.currentTimeMillis();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               Connection result=null;
               if (!pool.isEmpty()){
                   result =pool.removeFirst();
               }
               return result;
            }
        }
   }

   //释放链接
    public void releaseConnection(Connection connection){
        if (connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
                //通知等待的线程
            }
        }
    }


}
