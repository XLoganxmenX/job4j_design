package ru.job4j.io;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> list;
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            list = input.lines()
                    .filter(line -> line.length() > 0 && !line.startsWith("#"))
                    .collect(Collectors.toList());
            for (String line : list) {
                if (line.contains("#")) {
                    line = line.substring(0, line.indexOf("#"));
                }

                if (line.matches(".+={1}.+")) {
                    String[] word = line.split("=", 2);
                    values.put(word[0].trim(), word[1].trim());
                } else if (line.trim().length() > 0) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("Data/app.properties"));
    }
}
