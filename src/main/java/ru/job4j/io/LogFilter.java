package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;
    private static final String MESSAGE = " 404 ";

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new LinkedList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            list = input.lines().filter(line -> line.contains(MESSAGE)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(out)
                ))) {
            for (String line : data) {
                output.printf("%s%n", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
