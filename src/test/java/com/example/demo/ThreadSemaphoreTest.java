package com.example.demo;

import java.util.concurrent.*;

/**
 *  Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 * @author
 * @since
 */
public class ThreadSemaphoreTest {
    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }


    public static void testNewThreadPool(){
        // 创建一个可缓存的线程池，调用execute将重用以前构成的线程（如果线程可用）。
        // 如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移出那些已有60秒钟未被使用的线程。
        Executors.newCachedThreadPool();
        // 创建固定数目的线程池
        Executors.newFixedThreadPool(1);
        // 创建一个单线程化的Executor
        Executors.newSingleThreadExecutor();
        // 创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Time类。
        Executors.newScheduledThreadPool(1);
        /*为什么不推荐通过Executors直接创建线程池
        原因：
        java中BlockingQueue主要有两种实现，
        分别是ArrayBlockingQueue和LinkedBlockingQueue。ArrayBlockingQueue是一个用数组实现的有界阻塞队列，必须设置容量。
        而LinkedBlockingQueue是一个用链表实现的有界阻塞队列，容量可以选择进行设置，不设置的话，将是一个无边界的阻塞队列，最大长度为Integer.MAX_VALUE.
         查看new SingleExecutor时的源码可以发现，在创建LinkedBlockingQueue时，并未指定容量。
        此时，LinkedBlockingQueue就是一个无边界队列，对于一个无边界队列来说，是可以不断的向队列中加入任务的，这种情况下就有可能因为任务过多而导致内存溢出的问题。*/

        /*创建线程池的正确方法：
        避免使用Executors创建线程池，主要是避免使用其中的默认实现，那么我们可以自己直接调用ThreadPoolExecutor的构造函数自己创建线程池。在创建的同时，给BlockQueue指定容量就可以了。*/

        ExecutorService executor = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        /*这种情况下，一旦提交的线程数超过当前可用线程数时，就会抛出java.util.concurrent.RejectedExecutionException，
        这是因为当前线程池使用的队列是有界边界队列，队列已经满了便无法继续处理新的请求。但是异常（Exception）总比发生错误（Error）要好。*/

        // 多线程
        // CyclicBarrier
        // CountDownLatch
    }
}
