package ru.job4j.io.scanner.proba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextScanner {

    private static void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(System.getProperty("line.separator")); //пробел по умолчанию
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
