package com.felton.mybatismapper.util;

import com.felton.mybatismapper.entity.MakeArray;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
* 使用fork join进行整形数组相加
*/
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer> {
//        处理的数据分成10份
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
        private int[] src; // 表示我们要实际统计的数组
        private int fromIndex; // 开始统计的标
        private int toIndex; // 统计到哪里结束的下标

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i=fromIndex; i<=toIndex;i++) {
                    count = count + src[i];
                }
                return count;
            } else {
                int mid = (fromIndex + toIndex) /2 ;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left,right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        SumTask innerFind = new SumTask(src, 0, src.length-1);

        long start = System.currentTimeMillis();

        pool.invoke(innerFind); // 同步调用
        System.out.println("Task is Running ....");

        System.out.println("The count is "+ innerFind.join()
            + " spend time:"+(System.currentTimeMillis()-start) + " ms");
    }
}