package com.thread.test.syn;

public class SynClzAndIns {

    public static void main(String[] args) {

        //对于两个不同对象的对象锁  执行  互不影响
     /*   SynClzAndIns synClzAndIns=new SynClzAndIns();
        SynClzAndIns synClzAndIns2=new SynClzAndIns();

        InstanceSyn instanceSyn = new InstanceSyn(synClzAndIns);
        InstanceSyn2 instanceSyn2 = new InstanceSyn2(synClzAndIns2);
        new Thread(instanceSyn).start();
        new Thread(instanceSyn2).start();*/


        //相同对象的对象锁  执行就会有  先后顺序
        /*SynClzAndIns synClzAndIns=new SynClzAndIns();

        InstanceSyn instanceSyn = new InstanceSyn(synClzAndIns);
        InstanceSyn2 instanceSyn2 = new InstanceSyn2(synClzAndIns);
        new Thread(instanceSyn).start();
        new Thread(instanceSyn2).start();*/


        //类锁  与对象  可以同时执行  对象锁  类锁对象不同  可以并行执行
        //类锁  锁的是类的class对象 唯一的一份
        SynClzAndIns synClzAndIns=new SynClzAndIns();
        InstanceSyn instanceSyn = new InstanceSyn(synClzAndIns);
        new Thread(instanceSyn).start();

        SyncClass syncClass=new SyncClass();
        syncClass.start();


    }

    //使用类锁的线程
    private static class SyncClass extends Thread{
        @Override
        public void run() {
            System.out.println("SyncClass is running...");
            try {
                synClass();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //对象锁的使用
    private static class InstanceSyn implements Runnable{
        private SynClzAndIns synClzAndIns;

        public InstanceSyn(SynClzAndIns synClzAndIns) {
            this.synClzAndIns = synClzAndIns;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running!!!"+synClzAndIns);
            try {
                synClzAndIns.instance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class InstanceSyn2 implements Runnable{
        private SynClzAndIns synClzAndIns;

        public InstanceSyn2(SynClzAndIns synClzAndIns) {
            this.synClzAndIns = synClzAndIns;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running!!!"+synClzAndIns);
            try {
                synClzAndIns.instance2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private synchronized void instance() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("instance is going!!!"+this.toString());
        Thread.sleep(1000);
        System.out.println("instance is end!!!"+this.toString());
    }

    private synchronized void instance2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("instance2 is going!!!"+this.toString());
        Thread.sleep(1000);
        System.out.println("instance2 is end!!!"+this.toString());
    }

    private static synchronized void synClass() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("synClass is going!!!");
        Thread.sleep(1000);
        System.out.println("synClass is end!!!");
    }

}
