package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toString())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArgsName validateParameters(String[] args) {
        ArgsName keys = ArgsName.of(args);
        if (keys.get("d") == null || keys.get("e") == null || keys.get("o") == null) {
            throw new IllegalArgumentException("Need add: -d directory, -e exclude, -o output");
        }
        return keys;
    }

    public static void main(String[] args) throws IOException {
        ArgsName param = Zip.validateParameters(args);
        List<Path> paths = Search.search(
                Path.of(param.get("d")),
                p -> !p.toFile().getName().endsWith(param.get("e")));
        new Zip().packFiles(paths, Path.of(param.get("o")));
    }
}