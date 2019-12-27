package com.felton.mybatismapper.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindDirsFiles extends RecursiveAction {
//    当前任务需要搜索的目录
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    public static void main(String[] args) throws InterruptedException {
//        用一个ForkJoinPool实力调用总任务
        ForkJoinPool pool = new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("D:/"));

        pool.execute(task); // 异步调用

        System.out.println("Task is Running......");

        System.out.println("执行其他的任务");
        /*
        Thread.sleep(1000);
        int otherWork = 0;
        for(int i=0;i<100;i++){
            otherWork = otherWork+i;
        }
        System.out.println("Main Thread done sth......,otherWork="+otherWork);*/

        task.join();//阻塞的方法
        System.out.println("Task end");
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();

        // 列出所有的文件
        File[] files = path.listFiles();
        if (files!= null) {
            for (File file:files) {
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    if (file.getAbsolutePath().endsWith("txt")){
                        System.out.println("文件："+file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join(); // 等待子任务执行完成
                }
            }
        }
    }
}