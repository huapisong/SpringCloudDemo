package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

public class AES {
//    密钥
    private static String keyWord = "1234123412ABCDEF";
//    密钥偏移量
    private static String iv = "ABCDEF1234123412";
    /**
     * 加密
     * @param src 加密字符串
     * @param key 密钥
     * @return 加密后的字符串
     */
    public static String Encrypt(String src, String key) throws Exception {
        // 判断密钥是否为空
        if (key == null) {
            key = keyWord;
//            System.out.print("密钥不能为空");
//            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	// 算法/模式/补码方式
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
        return new Base64().encodeToString(encrypted);//base64
//        return binary(encrypted, 16); //十六进制
    }

    /**
     * 解密
     * @param src 解密字符串
     * @param key 密钥
     * @return 解密后的字符串
     */
    public static String Decrypt(String src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                key = keyWord;
//            System.out.print("密钥不能为空");
//            return null;
            }
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));

            byte[] encrypted1 = new Base64().decode(src);//base64
//            byte[] encrypted1 = toByteArray(src);//十六进制
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);	// 这里的1代表正数
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.isEmpty())
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static void main(String[] args) throws Exception {
        // 密钥
        String key = "1234123412ABCDEF";
        // 需要加密的字符串
        String src = "123456";
        System.out.println(src);
        // 加密
        String enString = Encrypt(src, key);
        System.out.println("加密后的字串是：" + enString);
        // 解密
        String DeString = Decrypt(enString, key);
        System.out.println("解密后的字串是：" + DeString);
    }
}
