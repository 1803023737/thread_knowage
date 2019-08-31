package com.xianliu.atom;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicMarkableReference
 * AtomicStampedReference
 *
 * 带有版本戳的原子操作类
 */
public class UseAtomicStampedRefrence {

    static AtomicStampedReference<String> asr=new AtomicStampedReference<String>("mark",0);

    public static void main(String[] args) throws InterruptedException {
        //获得初始版本号
        final int stamp = asr.getStamp();
        //初始版本值
        final String reference = asr.getReference();

        System.out.println(stamp+"==========="+reference);

        Thread rightStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"当前变量值："+reference+",当前版本戳："+stamp);
                //版本号迭代
                boolean b = asr.compareAndSet(reference, reference + "java", stamp, stamp + 1);
                System.out.println(b);
            }
        });
        //更新版本戳失败
        Thread errorStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String reference = asr.getReference();

                System.out.println(Thread.currentThread().getName()+"当前变量值："+reference+",当前版本戳："+asr.getStamp());
                //版本号迭代
                boolean flag = asr.compareAndSet(reference, reference + "C", stamp, stamp + 1);
                System.out.println(flag);

            }
        });
        //先执行正确版本戳线程
        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference()+"=============="+asr.getStamp());

    }

}
