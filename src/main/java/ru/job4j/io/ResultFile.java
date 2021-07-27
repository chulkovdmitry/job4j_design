package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int j = 1; j <= 9; j++) {
            for (int i = 9; i >= 1; i--) {
                int sum = j * i;
                s.append(j).append(" * ").append(i).append(" = ")
                        .append(sum).append(System.lineSeparator());
            }
        }
        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            out.write(s.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
