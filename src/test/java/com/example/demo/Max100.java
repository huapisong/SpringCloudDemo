package com.example.demo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Max100 {
    // p 的左右子节点已经为小根堆，调节 p 节点也为小根堆
    public static void adjust(long a[], int n, int p) {
        long t = a[p];
        for (int j = 2 * p + 1; j < n; j = 2 * p + 1) {
            if (j + 1 < n && a[j + 1] < a[j]) j++;
            if (a[j] > t) break;
            a[p] = a[j];
            p = j;
        }
        a[p] = t;

    }

    public static void k100() throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        long a[] = new long[100];
        for (int i = 0; i < 100; i++) {
            a[i] = scanner.nextLong();
        }
        //先读取100个数据
        for (int i = 100 / 2 - 1; i >= 0; i--)
            // 将前100个数据建立一个 大小为100 的小根堆
            adjust(a, 100, i);
        while (scanner.hasNextLong()) {
            //读取接下来的数据
            long k = scanner.nextLong();
            if (k < a[0])
                //如果比现在最小的数据还小，直接忽略
                continue;
            a[0] = k;
            //直接替换掉最小的数据
            adjust(a, 100, 0);
            //因为引入了大的数据，需要重新调整为一个小根堆
        }
        //最后的堆中保留的就是 前 100 大的数 //将一个小根堆进行排序,用堆排序思想
        for (int i = 100 - 1; i > 0; i--) {
            long t = a[0];
            a[0] = a[i];
            a[i] = t;
            adjust(a, i, 0);
        }
        for (int i = 0; i < 100; i++) System.out.println(a[i]);
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        randomData();
        long start = System.currentTimeMillis();
        k100();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //测试的电脑大概是1200 ms
    }

    public static void randomData() throws Exception {
        //随机100万数据
        File file = new File("input.txt");
        if (!file.exists()) file.createNewFile();
        PrintStream printStream = new PrintStream(new FileOutputStream(file));
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 1000000; i++) {
            long k = Math.abs(random.nextLong());
            printStream.println(k);
        }
        printStream.close();
    }



}
