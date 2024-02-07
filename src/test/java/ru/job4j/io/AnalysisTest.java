package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void whenServerShutDown(@TempDir Path temp) throws IOException {
        File source = temp.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.print("200 10:56:01" + System.lineSeparator()
                        + "500 10:57:01" + System.lineSeparator()
                        + "400 10:58:01" + System.lineSeparator()
                        + "500 10:59:01" + System.lineSeparator()
                        + "400 11:01:02" + System.lineSeparator()
                        + "300 11:02:02" + System.lineSeparator());
        }

        File target = temp.resolve("target.csv").toFile();

        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:57:01;11:02:02;").hasToString(result.toString());
    }

    @Test
    void whenServerShutDownTwoTimes(@TempDir Path temp) throws IOException {
        File source = temp.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.print("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "300 10:59:01" + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + "200 11:02:02" + System.lineSeparator());
        }

        File target = temp.resolve("target.csv").toFile();

        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(result.toString());
    }
}