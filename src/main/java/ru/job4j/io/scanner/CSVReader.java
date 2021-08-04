package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public void run(String path, String delimiter, String out, String filter)
            throws IOException {
        Path pathCSV = Path.of(path);
        List<String> ls = scan(pathCSV, delimiter);
        List<String> fl = doFilter(filter);
        List<Integer> li = getIndexes(ls, fl);
        if (out.equals("stdout")) {
            stdout(doOut(pathCSV, li, delimiter));
        } else {
            fileOut(out, doOut(pathCSV, li, delimiter));
        }
    }

    private List<String> doFilter(String filter) {
        return List.of(filter.split(","));
    }

    private List<Integer> getIndexes(List<String> ls, List<String> filter) {
        List<Integer> indexes = new ArrayList<>();
        for (String key : filter) {
            int index = ls.indexOf(key);
            if (index != -1) {
                indexes.add(index);
            }
        }
        return indexes;
    }

    private List<String> scan(Path path, String delimiter) {
        List<String> ls = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            String str = scanner.nextLine();
            ls = Arrays.asList(str.replace(" ", "").split(delimiter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    private StringBuilder doOut(Path path, List<Integer> getIndexes, String delimiter) {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                List<String> lss = Arrays.asList(scanner.nextLine().split(delimiter));
                for (Integer index : getIndexes) {
                    sb.append(lss.get(index)).append(delimiter);
                }
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    private void stdout(StringBuilder sb) {
        System.out.println(sb);
    }

    private void fileOut(String out, StringBuilder sb) throws IOException {
        try (FileWriter writer = new FileWriter(out)) {
            writer.write(String.valueOf(sb));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void check(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Root folder and file extension is missing."
                            + " Usage java -jar dir.jar ROOT_FOLDER DELIMITER OUTPUT FILTER"
                            + " For example:"
                            + " -path=file.txt -delimiter=; -out=stdout -filter=name,age");
        }
    }

    public void init(String[] args) throws IOException {
        check(args);
        ArgsName arg = ArgsName.of(args);
        run(arg.get("path"), arg.get("delimiter"), arg.get("out"), arg.get("filter"));
    }

    public static void main(String[] args) throws IOException {
        CSVReader csv = new CSVReader();
        csv.init(args);
    }
}