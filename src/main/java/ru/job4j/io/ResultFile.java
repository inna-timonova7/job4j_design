package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static String[] multiplicationTable(int num) {
        String[] str = new String[12];
        for (int i = 0; i < 12; i++) {
            str[i] = num + " * " + i + " = " + i * num;
        }
        return str;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/result.txt")) {
            for (String string : multiplicationTable(10)) {
                out.write(string.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
