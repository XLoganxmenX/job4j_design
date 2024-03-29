package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    private final String file;
    private static final String MESSAGE = "404";

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> outputList = new LinkedList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line : input.lines().toList()) {
                String[] word = line.split("\\W+");
                if (MESSAGE.equals(word[word.length - 2])) {
                    outputList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputList;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(out)
                ))) {
            for (String line : data) {
                output.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
