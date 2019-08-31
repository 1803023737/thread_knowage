package com.thread.test.forkjoin;

import java.util.concurrent.RecursiveAction;

public class PrintTask extends RecursiveAction{

    private static final int THRESHOLD=50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
      if (end-start<THRESHOLD){

          for (int i = start; i < end; i++) {
              System.out.println("线程："+Thread.currentThread().getName()+",打印数字："+i);
          }

      }else{
          int middle=(end+start)/2;
          PrintTask left = new PrintTask(start, middle);
          PrintTask right = new PrintTask(middle, end);
          left.fork();
          right.fork();
      }

    }
}
