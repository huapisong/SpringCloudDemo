package com.example.demo;

import java.util.ArrayList;

public class Solution {
    /**
     * @param k: The number k.
     * @return: The kth prime number as description.
     */
    public long kthPrimeNumber(int k) {
        if (k <= 0) return -1;
        ArrayList<Long> nums = new ArrayList<Long>();
        nums.add(1L);
        for (int i = 0; i < k; i++) {
            long minNextUgly = Math.min(nextUgly(nums, 3), nextUgly(nums, 5));
            minNextUgly = Math.min(minNextUgly, nextUgly(nums, 7));
            nums.add(minNextUgly);
        }
        return nums.get(nums.size() - 1);
    }

    private long nextUgly(ArrayList<Long> nums, int factor) {
        int size = nums.size();
        int start = 0, end = size - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) * factor > nums.get(size - 1)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums.get(start) * factor > nums.get(size - 1)) {
            return nums.get(start) * factor;
        }
        return nums.get(end) * factor;
    }

    public static void main(String[] args) {
        /*Solution solution = new Solution();
        long l = solution.kthPrimeNumber(10);
        System.out.println(l);*/
        int[][] array = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
                };
        boolean bool = find(5, array);
        boolean bool1 = find(25, array);
        System.out.println(bool);
        System.out.println(bool1);

    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int rows = array.length, cols = array[0].length;
        int r = 0, c = cols - 1;

        while (r <= rows - 1 && c >= 0) {
            if (target == array[r][c]) {
                return true;
            } else if (target > array[r][c]) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}
