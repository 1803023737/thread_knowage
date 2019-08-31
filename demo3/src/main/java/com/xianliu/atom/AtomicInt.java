package com.xianliu.atom;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInt {

    static AtomicInteger ai=new AtomicInteger(10);

    public static void main(String[] args) {
        //先get的值
        System.out.println(ai.getAndIncrement());
        //get是提升之后的值
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());


    }

}
