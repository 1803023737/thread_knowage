package com.thread.test.join;

public class UseJoin {

    static class JumpQueue implements Runnable{

        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程："+Thread.currentThread().getName()+" terminted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName()+ " jump a queue the thread:"+thread.getName());
            thread.start();
            previous=thread;
        }

        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+" terminited!");

    }


}
