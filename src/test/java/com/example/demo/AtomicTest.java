package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicTest {
    public static void main(String[] args) {
        //
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // 创建线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000).forEach(i -> executorService.submit(atomicInteger::incrementAndGet));
        executorService.shutdown();
       // stop(executorService);
        System.out.println(atomicInteger.get());

    }
}
