package com.thread.test.practise;

import java.util.List;

public class NumEngEntity {

    private static final StringBuffer stringBuffer=new StringBuffer();
    private List<Integer> numList;
    private List<Character> engList;

    public NumEngEntity(List<Integer> numList, List<Character> engList) {
        this.numList = numList;
        this.engList = engList;
    }

    public synchronized void printNum() throws InterruptedException {
        while (true){
            if (numList.size()!=0){
                if (stringBuffer.toString().length()>0&&stringBuffer.toString().length()%3==2){
                    System.out.println("当前线程为："+Thread.currentThread().getName()+",等待打印字母。");
                    wait();
                    System.out.println("当前线程为："+Thread.currentThread().getName()+",打印字母完毕！");
                }else{

                        System.out.println("当前线程为："+Thread.currentThread().getName()+",拼接数字："+numList.get(0));
                        stringBuffer.append(numList.remove(0));
                        notifyAll();
                        System.out.println("当前线程为："+Thread.currentThread().getName()+",拼接数字完毕！"+stringBuffer.toString());

                }
            }else{
                System.out.println("数字容器空了！");
                break;
            }
        }
    }

    public synchronized void printEng() throws InterruptedException {
        char a[] = new char[26];
        while (true){
            if (engList.size()!=0){
            if (stringBuffer.toString().length()%3!=2){
                System.out.println("===当前线程为："+Thread.currentThread().getName()+",等待打印数字。");
                wait();
                System.out.println("===当前线程为："+Thread.currentThread().getName()+",打印数字完毕！");
            }else{
                System.out.println("===当前线程为："+Thread.currentThread().getName()+",拼接字母："+engList.get(0));
                stringBuffer.append(engList.remove(0));
                notifyAll();
                System.out.println("===当前线程为："+Thread.currentThread().getName()+",拼接字母完毕！"+stringBuffer.toString());
            }
            }else{
                System.out.println("字母容器空了！");
                break;
            }
        }
    }

}
