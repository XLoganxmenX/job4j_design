package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(name, user.name)
                && children == user.children
                && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        User user2 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        System.out.println(user1.equals(user2));
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        System.out.printf("%s, hashcode: %s, hash: %s, bucket: %s\n", user1, hashCode1, hash1, bucket1);

        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        System.out.printf("%s, hashcode: %s, hash: %s, bucket: %s\n", user2, hashCode2, hash2, bucket2);



        Map<User, Object> userMap = new HashMap<>(16);
        userMap.put(user1, new Object());
        userMap.put(user2, new Object());

        for (User user : userMap.keySet()) {
            int hashCode = user.hashCode();
            int hash = hashCode ^ (hashCode >>> 16);
            int bucket = hash & 15;
            System.out.printf("%s, hashcode: %s, hash: %s, bucket: %s\n", userMap.get(user), hashCode, hash, bucket);
        }
        System.out.println(Objects.hash(user1.hashCode() % 16));
        System.out.println(Objects.hash(user2.hashCode() % 16));


    }
}