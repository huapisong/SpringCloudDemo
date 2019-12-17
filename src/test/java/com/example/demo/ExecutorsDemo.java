package com.example.demo;

import java.util.concurrent.*;

public class ExecutorsDemo {

    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {
        // Thread
       /* MyThread T1 = new MyThread("A");
        MyThread T2 = new MyThread("B");
        T1.start();
        T2.start();*/

        // Executor
       /* for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(new SubThread());
        }*/

        // Runnable
        /*executor.execute(new MyRunnable());*/

        // Callable
        Future future1 = executor.submit(new MyCallable());
        Future future2 = executor.submit(new MyCallable());
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //  shutdown() 停止接收新任务，原来的任务继续执行
        /*1、停止接收新的submit的任务；
        2、已经提交的任务（包括正在跑的和队列中等待的）,会继续执行完成；
        3、等到第2步完成后，才真正停止；*/
        executor.shutdown();

        // ShutdownNow() 停止接收新任务，原来的任务停止执行
        /*1、跟 shutdown() 一样，先停止接收新submit的任务；
        2、忽略队列里等待的任务；
        3、尝试将正在执行的任务interrupt中断；
        4、返回未执行的任务列表；*/

        // awaitTermination(long timeOut, TimeUnit unit)：当前线程阻塞
        /*当前线程阻塞，直到：

        等所有已提交的任务（包括正在跑的和队列中等待的）执行完；
        或者 等超时时间到了（timeout 和 TimeUnit设定的时间）；
        或者 线程被中断，抛出InterruptedException*/

        /*区别
        1、shutdown() 和 shutdownNow() 的区别

        shutdown() 只是关闭了提交通道，用submit()是无效的；而内部该怎么跑还是怎么跑，跑完再停。
        shutdownNow() 能立即停止线程池，正在跑的和正在等待的任务都停下了。
        2、shutdown() 和 awaitTermination() 的区别

        shutdown() 后，不能再提交新的任务进去；但是 awaitTermination() 后，可以继续提交。

        awaitTermination()是阻塞的，返回结果是线程池是否已停止（true/false）；shutdown() 不阻塞。*/

        /*总结
        1、优雅的关闭，用 shutdown()
        2、想立马关闭，并得到未执行任务列表，用shutdownNow()
        3、优雅的关闭，并允许关闭声明后新任务能提交，用 awaitTermination()
        4、关闭功能 【从强到弱】 依次是：shuntdownNow() > shutdown() > awaitTermination()*/
    }

}
class MyThread extends Thread {
    private String name;
    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+":"+i);
            try {
                sleep(1000); //休眠1秒，避免太快导致看不到同时执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class SubThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) { //do nothing
        }
    }
}

class MyRunnable implements Runnable{

    public void run(){
        System.out.println("Runnable:run()....");
        int i=0;
        while(i<20){
            i++;
            for(int j=0;j<1000000;j++);
            System.out.println("i="+i);
        }
    }
}


class MyCallable implements Callable<String> {
    public String call() throws Exception {
        System.out.println("开始执行Callable");
        String[] ss={"zhangsan","lisi"};
        long[] num=new long[2];
        for(int i=0;i<1000000;i++){
            num[(int)(Math.random()*2)]++;
        }

        if(num[0]>num[1]){
            return ss[0];
        }else if(num[0]<num[1]){
            throw new Exception("弃权!");
        }else{
            return ss[1];
        }
    }

}