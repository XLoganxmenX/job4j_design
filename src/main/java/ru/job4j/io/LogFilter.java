package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
