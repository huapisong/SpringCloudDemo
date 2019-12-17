package com.example.demo;

//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 字节缓存
 */
public class BufferTest {
    public static void main(String[] args) {
        //ByteBuffer.allocate(10);
        // 初始化位数
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        buffer.put(0, (byte)'M').put((byte) 'W');
        System.out.println(buffer.toString());
        buffer.clear();

        // 子数组最大的和
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = maxSubArray(nums);
        System.out.println(maxSum);
    }
    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = nums[0];
        int maxSum = preSum;
        for (int i = 1; i < nums.length; i++) {
            System.out.println(preSum);
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            System.out.println(preSum);
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int maxSubArray(int[] nums) {
        int sum = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            System.out.println(nums[i] + ":" + sum + nums[i]);
            sum = Math.max(nums[i], sum + nums[i]);
            res = Math.max(res, sum);
            System.out.println(res + ":" + sum);
        }
        return res;
    }

//    public final static String getIpAddress(HttpServletRequest request) throws IOException {
//        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
//        String ip = request.getHeader("X-Forwarded-For");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("Proxy-Client-IP");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("WL-Proxy-Client-IP");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("HTTP_CLIENT_IP");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getRemoteAddr();
//            }
//        }else if (ip.length() > 15) {
//            String[] ips = ip.split(",");
//            for (int index = 0; index < ips.length; index++) {
//                String strIp = (String) ips[index];
//                if (!("unknown".equalsIgnoreCase(strIp))) {
//                    ip = strIp;
//                    break;
//                }
//            }
//        }
//        return ip;
//    }




}
