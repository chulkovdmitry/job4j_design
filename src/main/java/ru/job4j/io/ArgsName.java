package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public Set<String> getKeys() {
        return values.keySet();
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters is null!");
        }
        Arrays.stream(args).filter(e -> e.contains("="))
                .forEach(e -> {
                    String[] line = e.split("=");
                    if (line.length < 2) {
                        throw new IllegalArgumentException("Value parameter is null!");
                    }
                    values.put(line[0].substring(1), line[1]);
                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

}
