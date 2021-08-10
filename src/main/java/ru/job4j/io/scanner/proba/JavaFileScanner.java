package ru.job4j.io.scanner.proba;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class JavaFileScanner {

    public static void main(String[] args) throws IOException {

        String fileName = "/Users/prologistic/source.txt";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        //построчно считываем файл
        scanner.useDelimiter(System.getProperty("line.separator"));
        while(scanner.hasNext()) {
            System.out.println("Строка: " + scanner.next());
        }
        scanner.close();
        //считываем CSV файл и парсим его в массив объектов
        /**
         * Андрей,20,мужчина
         * Оля,25,женщина
         * Витя,30,мужчина
         */
        scanner = new Scanner(Paths.get("/Users/prologistic/data.csv"));
        scanner.useDelimiter(System.getProperty("line.separator"));
        while(scanner.hasNext()) {
            //парсим строку в объект Employee
            Employee emp = parseCSVLine(scanner.next());
            System.out.println(emp.toString());
        }
        scanner.close();

        //читаем с System.in;
        scanner = new Scanner(System.in);
        System.out.println("Вводим первое слово: " + scanner.next());
    }

    private static Employee parseCSVLine(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter("\\s*,\\s*");
        String name = scanner.next();
        int age = scanner.nextInt();
        String gender = scanner.next();
        JavaFileScanner jfs = new JavaFileScanner();
        return jfs.new Employee(name, age, gender);
    }

    public class Employee {
        private String name;
        private int age;
        private String gender;

        public Employee(String n, int a, String gen) {
            this.name = n;
            this.age = a;
            this.gender = gen;
        }

        @Override
        public String toString() {
            return "Name=" + this.name + "::Age=" + this.age + "::Gender=" + this.gender;
        }
    }

}
