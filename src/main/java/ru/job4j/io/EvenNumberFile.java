package ru.job4j.io;

import java.io.IOException;
import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int num = Integer.parseInt(line);
                System.out.println(num % 2 == 0
                        ? num + " is even number" : num + " is uneven number");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
