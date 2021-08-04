package ru.job4j.io.scanner;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CSVReaderTest {

    @Test
    public void whenNameAndAgeOnFilter() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Path result = Files.createTempFile("result", ".txt");
        Files.writeString(tempFile, "name, age, birthDate, education, children"
                + System.lineSeparator()
                + "Ivan, 33, 02.02.1988, Yes, 3"
                + System.lineSeparator()
                + "Mary, 28, 01.10.1993, No, 2");
        CSVReader csv = new CSVReader();
        String path = tempFile.toAbsolutePath().toString();
        String[] args = {"-path=" + path,
                "-delimiter=,",
                "-out=" + result.toAbsolutePath(),
                "-filter=name,age"};
        csv.init(args);
        String expected = "name, age,"
                + System.lineSeparator()
                + "Ivan, 33,"
                + System.lineSeparator()
                + "Mary, 28,";
        String resultFileContent = Files
                .lines(result, StandardCharsets.UTF_8)
                .collect(Collectors.joining(System.lineSeparator()));
        assertThat(expected, is(resultFileContent));
    }
}