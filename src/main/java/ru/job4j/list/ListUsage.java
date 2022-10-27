package ru.job4j.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> rsl2 = new ArrayList<>(List.of("1", "2", "3", "4", "5"));
        int size = rsl2.size();
        List<String> rsl3 = List.of("ein", "zwei", "drei");
        List<String> rsl4 = new ArrayList<>();
        List<String> list5 = rsl2.subList(1, 2);
        rsl4.add("один");
        rsl4.add("два");
        rsl4.add("три");
        rsl4.add("четыре");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(3, "four");
        list.add("five");
        list.add("six");
        list.add("seven");
        rsl.addAll(0, list);
        rsl.set(1, "two and second");
        rsl.replaceAll(String::toUpperCase);
        rsl.remove(5);
        rsl.remove("SEVEN");
//        rsl4.removeAll(list);
//        rsl4.retainAll(list);
        rsl4.removeIf(s -> s.length() <= 2);
//        rsl.sort(Comparator.reverseOrder());

        for (String s : rsl2) {
            System.out.println("Текущий элемент: " + s);
            System.out.println("Текущий элементус: " + s);
        }

        for (int i = 0; i < rsl.size(); i++) {
            System.out.println("Текущий элемент: " + rsl.get(i));

        }

        Iterator<String> iterator = rsl3.listIterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }

        Iterator<String> iterator2 = rsl2.listIterator(2);
        while (iterator2.hasNext()) {
            System.out.println("Текущий элемент: " + iterator2.next());
            System.out.println("Размер списка равен: " + size);
        }

        for (String s : rsl4) {
            boolean b = rsl4.contains("четыре");
            int i = rsl4.indexOf("два");
            int i2 = rsl4.indexOf("two");
            int i3 = rsl4.lastIndexOf("четыре");
            System.out.println("Текущий элемент: " + s);
            System.out.println("Текущий элемент: " + b);
            System.out.println("Текущий элемент: " + i);
            System.out.println("Текущий элемент: " + i2);
            System.out.println("Текущий элемент: " + i3);
        }
    }
}