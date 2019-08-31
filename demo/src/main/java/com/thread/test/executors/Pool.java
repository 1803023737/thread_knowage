package com.thread.test.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Thread thread = new Thread(new subThread());
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.shutdown();
    }

}

class subThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread()+":当前序号："+i);
        }
    }
}

