package com.example.demo;

import java.util.Vector;

/**
 *  Vector
 */
public class VectorTest {

    public static void main(String[] args) {
        // 初始化Vector
        Vector<String> vector = new Vector<>();
        vector.add("init_0");
        vector.add("init_1");
        vector.add("init_2");
        new Thread(() -> getLast(vector)).start();
        new Thread(() -> deleteLast(vector)).start();
        new Thread(() -> getLast(vector)).start();
        new Thread(() -> deleteLast(vector)).start();

    // 遍历Vector
        for (int i = 0; i < vector.size(); i++) {

            // 比如在这执行vector.clear();
            //new Thread(() -> vector.clear()).start();

            System.out.println(vector.get(i));
        }

    }

    // 得到Vector最后一个元素
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    // 删除Vector最后一个元素
    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
