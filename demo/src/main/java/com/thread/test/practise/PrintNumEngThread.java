package com.thread.test.practise;

public class PrintNumEngThread extends Thread {

    private NumEngEntity numEngEntity;

    public PrintNumEngThread(NumEngEntity numEngEntity){
        this.numEngEntity=numEngEntity;
    }

    public void init(){
        try {
            numEngEntity.printNum();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            numEngEntity.printEng();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
