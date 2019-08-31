package com.limit.xianliu.fenbushi;

import redis.clients.jedis.Jedis;

public class JedisDemo {

    public static void main(String[] args) {
        //分布式容器
        Jedis jedis = new Jedis("localhost", 6379);

        String key="limit-orders";
        long ret=jedis.incr(key);
        boolean flag=true;
        if (ret==1l){
            System.out.println("第一次");
            jedis.expire(key,30);
        }else{
            if (ret>10){
                flag=false;
            }
        }
        System.out.println("限流结果："+flag+"  "+ret);
        jedis.close();
    }

}
