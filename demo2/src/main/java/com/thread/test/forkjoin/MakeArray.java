package com.thread.test.forkjoin;

import java.util.Random;

public class MakeArray {

    //数组长度
    public static final int ArrayLength=4000;

    public static int[] makeArray(){

        Random random=new Random();
        int [] array=new int[ArrayLength];
        for (int i = 0; i < ArrayLength; i++) {
           array[i]=random.nextInt(ArrayLength*3);
        }
        return array;
    }

}
