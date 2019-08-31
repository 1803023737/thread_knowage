package com.thread.test.forkjoin.yibu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 找寻指定目录下指定类型文件
 */
public class FindDirsFiles extends RecursiveAction{

    //当前任务需要搜寻的目录
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        //定义任务容器
        List<FindDirsFiles> subTasks=new ArrayList<>();

        File[] files = path.listFiles();
        if (files!=null){
            for (File file : files) {
                if (file.isDirectory()){
                    //目录类型   任务容器添加任务
                    subTasks.add(new FindDirsFiles(file));
                }else{
                    //文件   检查
                    //System.out.println(file.getAbsolutePath());
                    if (file.getAbsolutePath().endsWith("txt")){
                        System.out.println("文件："+file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()){
                //提交任务
                for (FindDirsFiles subtask : invokeAll(subTasks)) {
                    //执行任务
                    subtask.join();
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ForkJoinPool pool=new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("F:/"));
        //异步
        pool.execute(task);
        System.out.println("task is running");
        Thread.sleep(1000);
        int count=0;
        for (int i = 0; i < 1000; i++) {
            count+=i;
        }
        System.out.println("main thread done ,count:"+count);
        task.join();
        System.out.println("task is ending");
    }

}
