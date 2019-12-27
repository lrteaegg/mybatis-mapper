package com.felton.mybatismapper.entity;

import java.util.Random;

/**
* 生成整形数组
*/
public class MakeArray {
//    数组长度
    public static final int ARRAY_LENGTH = 100000000;

    public static int[] makeArray() {
        Random r = new Random();

        int[] result = new int[ARRAY_LENGTH];
        for (int i=0; i<ARRAY_LENGTH; i++) {
            result[i] = r.nextInt(ARRAY_LENGTH*3);

        }
        return  result;
    }
}