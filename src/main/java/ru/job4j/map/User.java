package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
        public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && (name == user.name
                || (name != null && name.equals(user.name))) && (birthday == user.birthday
                || (birthday != null && birthday.equals(user.birthday)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Jim", 3, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("Jim", 3, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("user1 - хэшкод: %s, хэш1: %s, бакет1: %s",
                String.valueOf(hashCode1), hash1, bucket1);
        System.out.printf("user2 - хэшкод: %s, хэш2: %s, бакет2: %s",
                String.valueOf(hashCode2), hash2, bucket2);
        System.out.println("------");
        System.out.println("user1 = " + user1);
        System.out.println("hashCode1 = " + hashCode1);
        System.out.println("hash1 = " + hash1);
        System.out.println("bucket1 = " + bucket1);
        System.out.println("user2 = " + user2);
        System.out.println("hashCode2 = " + hashCode2);
        System.out.println("hash2 = " + hash2);
        System.out.println("bucket2 = " + bucket2);
        System.out.println("result + " + user1.equals(user2));
        System.out.println(user1.hashCode() == user2.hashCode());
        System.out.println(System.identityHashCode(user1));
        System.out.println(System.identityHashCode(user2));
    }
}
