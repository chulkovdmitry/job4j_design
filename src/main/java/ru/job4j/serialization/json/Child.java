package ru.job4j.serialization.json;

public class Child {
    private final String name;
    private final int age;

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{"
                + "name='" + name + '\''
                + ", age=" + age + '}';
    }
}
