package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> files = new HashSet<>();
    private List<Path> duplicates = new ArrayList<>();

    public List<Path> getFoundDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var property = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!files.contains(property)) {
            files.add(property);
        } else {
            duplicates.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
