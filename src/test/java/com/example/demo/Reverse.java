package com.example.demo;

import java.util.Stack;

/**
 * String 的反转
 */
public class Reverse {
    private String str = null;

    public Reverse(String str) {
        this.str = str;
    }

    // 数组实现String反转
    public String reverseByArray() {
        if (str == null || str.length() == 1) {
            return str;
        }
        char[] ch = str.toCharArray();// 字符串转换成字符数组
        for (int i = 0; i < ch.length / 2; i++) {
            char temp = ch[i];
            ch[i] = ch[ch.length - i - 1];
            ch[ch.length - i - 1] = temp;
        }
        return new String(ch);
    }

    // 用栈实现String反转
    public String reverseByStack() {
        if (str == null || str.length() == 1) {
            return str;
        }
        Stack<Character> stack = new Stack<Character>();
        char[] ch = str.toCharArray();// 字符串转换成字符数组
        for (char c : ch) {
            stack.push(c);// 每个字符，推进栈
        }
        for (int i = 0; i < ch.length; i++) {
            ch[i] = stack.pop();// 移除这个堆栈的顶部对象
        }
        return new String(ch);
    }

    // 用逆序遍历实现String反转
    public String reverseBySort() {
        if (str == null || str.length() == 1) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));// 使用StringBuffer从右往左拼接字符
        }
        return sb.toString();
    }

    // 使用位运算实现String反转
    public String reverseByBit() {
        if (str == null || str.length() == 1) {
            return str;
        }
        char[] ch = str.toCharArray();// 字符串转换成字符数组
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            ch[i] ^= ch[len - 1 - i];
            ch[len - 1 - i] ^= ch[i];
            ch[i] ^= ch[len - 1 - i];
        }
        return new String(ch);
    }

    //使用递归实现String反转
    public String reverseByRecursive(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() == 1) {
            return str;
        } else {
            // 从下标为1开始截取字符串，在返回下标为0的字符
            return reverseByRecursive(str.substring(1)) + str.charAt(0);
        }
    }

    // 使用 StringBuilder reverse() 方法反转
    public String reverseByStringBuilder(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "123456";

        Reverse r = new Reverse(s);
        System.out.println(r.reverseByArray());
        System.out.println(r.reverseByStack());
        System.out.println(r.reverseBySort());
        System.out.println(r.reverseByBit());
        System.out.println(r.reverseByRecursive(s));
        System.out.println(r.reverseByStringBuilder(s));
    }
}
