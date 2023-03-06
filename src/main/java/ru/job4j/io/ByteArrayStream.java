package ru.job4j.io;

import java.io.*;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[] {'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = byteStream2.read()) != -1) {
            System.out.print((char) data1);
        }
        System.out.println();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outputStream.writeBytes(bytes2);
        System.out.println(outputStream);
        byte[] byteArray = outputStream.toByteArray();
        System.out.println();
        try (FileOutputStream fileOutput = new FileOutputStream("data/message.txt")) {
            outputStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
