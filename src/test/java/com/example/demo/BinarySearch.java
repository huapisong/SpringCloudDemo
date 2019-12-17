package com.example.demo;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] srcArray, int des) {
        //定义初始最小、最大索引
        int low = 0;
        int high = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (low <= high) {
            //计算出中间索引值
            int middle = (high + low) >>> 1;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                high = middle - 1;
                //判断上限
            } else {
                low = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }

    public static void main(String[] args) {
        int[] srcArray = new int[]{6, 7, 8, 9, 0, 4, 5, 1, 2, 3};
        bubbleSort(srcArray);

        Arrays.sort(srcArray);
        System.out.println(Arrays.toString(reverse(srcArray)));
        // 二分搜索法(使用之前需要先排序)


        int des = 5;
        int index = binarySearch(srcArray, des);
        System.out.println(index);
        if (index != -1) {
            System.out.println(srcArray[index]);
        }
    }

    public static void bubbleSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    // 反转
    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
