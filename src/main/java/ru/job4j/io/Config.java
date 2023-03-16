package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(s -> !s.startsWith("#") && !s.isEmpty())
                    .forEach(s -> {
                        String[] str = s.split("=", 2);
                        checkString(s);
                        values.put(str[0], str[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkString(String str) {
        boolean rsl = false;
        String[] strings = str.split("=", 2);
        if (strings.length == 2 && strings[0].isBlank() && strings[1].isBlank()) {
            throw new IllegalArgumentException("Mistake in the key=value pattern");
        }
        rsl = true;
        return rsl;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
