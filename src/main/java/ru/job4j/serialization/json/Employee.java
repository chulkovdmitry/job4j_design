package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        JSONObject jsonChildren = new JSONObject("{\"Oleg\":\"7\"}");
        List<String> list = new ArrayList<>();
        list.add("Worker");
        list.add("Fan");
        JSONArray jsonDuties = new JSONArray(list);
        final Employee employee = new Employee(
                true, 32, new Child("Oleg", 7), "worker", "fan");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", employee.isSex());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("children", jsonChildren);
        jsonObject.put("duties", jsonDuties);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(employee).toString());
    }
}
