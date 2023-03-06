package ru.job4j.io;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintUsage {

    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            stream.println("Из PrintStream в FileOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintStream stream2 = new PrintStream(new FileOutputStream("data/print2.txt"))) {
            stream2.println("Из PrintStream в FileOutputStream_2");
            stream2.write("Новая строка".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
