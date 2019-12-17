package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流(Character Streams)
 */
public class CopyCharacters {
    public static void main(String[] args) throws IOException {
        //
        FileReader inputStream = null;
        FileWriter outputStream = null;
        try {

            inputStream = new FileReader("D:\\bak\\code\\springbootdemo\\src\\test\\java\\com\\example\\resources\\xanadu.txt");
            outputStream = new FileWriter("D:\\bak\\code\\springbootdemo\\src\\test\\java\\com\\example\\resources\\outagain.txt");
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        }


    }
}
