package com.thread.test.practise;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> numList=new ArrayList<>();
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);
        numList.add(6);
        numList.add(7);
        List<Character> engList=new ArrayList<>();
        engList.add('a');
        engList.add('b');
        engList.add('c');
        NumEngEntity numEngEntity = new NumEngEntity(numList,engList);
        new PrintNumEngThread(numEngEntity).start();
        new PrintNumEngThread(numEngEntity).init();
        System.out.println("程序结束！");
    }

}
