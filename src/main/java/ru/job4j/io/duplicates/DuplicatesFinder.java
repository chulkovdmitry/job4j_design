package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Format;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        var visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), visitor);
        visitor.getFoundDuplicates().forEach(System.out::println);
    }
}