package com.example.demo;

import java.util.HashSet;
import java.util.Iterator;

public class TreeSetTestError {
    public static void main(String[] args) {
//        //
//        TreeSet ts = new TreeSet();
//        // 向TreeSet集合中添加两个Err对象
//        ts.add(new Error());
//        //  当添加第二个Date对象时，TreeSet就好调用该对象的compareTo(Object obj)方法与集合中其他元素进行比较，则此时程序会引发异常
//        //  java.lang.ClassCastException: java.lang.Error cannot be cast to java.lang.Comparable
//        // ts.add(new Error());
//        // 当操作TreeSet里的集合数据时，不同类型的元素依然会发生ClassCastExceptio异常。
//        ts.add(new Object());

       /* TreeSet treeSet = new TreeSet();
        Z z1 = new Z(6);
        treeSet.add(z1);
        System.out.println(treeSet.add(z1));
        //下面输出set集合，将看到有2个元素
        System.out.println(treeSet);
        //修改set集合的第一个元素的age属性
        ((Z) (treeSet.first())).age = 9;
        //输出set集合的最后一个元素的age属性，将看到也变成了9
        System.out.println(((Z) (treeSet.last())).age);*/


        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));
        //打印TreeSet集合，集合元素是有序排列的
        System.out.println(hs);
        //取出第一个元素
        Iterator it = hs.iterator();
        R first = (R) it.next();
        //为第一个元素的count属性赋值
        first.count = -3;
        //再次输出count将看到TreeSet里的元素处于无序状态
        System.out.println(hs);
        hs.remove(new R(-3));
        System.out.println(hs);
        //输出false
        System.out.println("hs是否包含count为-3的R对象？" + hs.contains(new R(-3)));
        //输出false
        System.out.println("hs是否包含count为5的R对象？" + hs.contains(new R(5)));

    }

    static class Z implements Comparable {
        int age;

        public Z(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int compareTo(Object o) {
            return 1;
        }
    }

    static class R {
        int count;

        public R(int count) {
            this.count = count;
        }

        public String toString() {
            return "R(count属性:" + count + ")";
        }

        public boolean equals(Object obj) {
            if (obj instanceof R) {
                R r = (R) obj;
                if (r.count == this.count) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.count;
        }
    }
}
