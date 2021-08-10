package ru.job4j.io.scanner.proba;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DataScanner {
    private static void readFile(String fileName) {
        try {
            Scanner scanner =
                    new Scanner(new File(fileName));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                parseLine(scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void parseLine(String line) {
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("\\s*,\\s*");
        String name = lineScanner.next();
        int age = lineScanner.nextInt();
        boolean isCertified = lineScanner.nextBoolean();
        System.out.println("It is " + isCertified
                + " that " + name + ", age "
                + age + ", is certified.");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java TextScanner "
                    + "file location");
            System.exit(0);
        }
        readFile(args[0]);
    }
}
