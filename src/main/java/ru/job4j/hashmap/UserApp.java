package ru.job4j.hashmap;

import java.util.*;

public class UserApp {
    public static void main(String[] args) {

        User one = new User("Igor", 2, (
                new GregorianCalendar(1992, 11, 7)));
        User two = new User("Igor", 2, (
                new GregorianCalendar(1992, 11, 7)));

        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(one, new  Object());
        userObjectMap.put(two, new  Object());

        userObjectMap.entrySet().forEach(
                userObjectEntry -> System.out.println(userObjectEntry.getKey() + " " + userObjectEntry.getValue()));
    }
}
