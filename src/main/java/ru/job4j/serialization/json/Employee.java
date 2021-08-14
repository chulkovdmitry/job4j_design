package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Employee {
    private final boolean sex;
    private final int age;
    private final Child child;
    private final String[] duties;

    public Employee(boolean sex, int age, Child child, String... duties) {
        this.sex = sex;
        this.age = age;
        this.child = child;
        this.duties = duties;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "sex=" + sex
                + ", age=" + age
                + ", child=" + child
                + ", duties=" + Arrays.toString(duties) + '}';
    }

    public static void main(String[] args) {
        Employee emp = new Employee(true, 32, new Child("Oleg", 7), "worker", "fan");
        Gson gson = new GsonBuilder().create();
        String gsonEmp = gson.toJson(emp);
        System.out.println(gsonEmp);
        Employee empMod = gson.fromJson(gsonEmp, Employee.class);
        System.out.println(empMod);
    }
}
