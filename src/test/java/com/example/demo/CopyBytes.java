package com.example.demo;

import java.io.*;

/**
 * 字节流(Byte Streams)
 *
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {
        //
        FileInputStream in = null;
        FileOutputStream out = null;
        // 获取项目路径
        System.out.println(new File(".").getAbsolutePath());
        try {
           String path =  File.separator + "aaa.txt";

            in = new FileInputStream("D:\\bak\\code\\springbootdemo\\src\\test\\java\\com\\example\\resources\\xanadu.txt");
            out = new FileOutputStream("D:\\bak\\code\\springbootdemo\\src\\test\\java\\com\\example\\resources\\outagain.txt");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if ( in != null) in.close();
            if (out != null) out.close();
        }

    }
}
