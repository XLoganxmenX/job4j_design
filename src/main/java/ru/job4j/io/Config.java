package ru.job4j.io;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            input.lines()
                    .filter(line -> line.length() > 0 && !line.startsWith("#"))
                    .map(this::normalize)
                    .filter(this::isMatch)
                    .forEach(line -> {
                        String[] word = line.split("=", 2);
                        if (word.length == 2) {
                            values.put(word[0].trim(), word[1].trim());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String normalize(String line) {
        if (line.contains("#")) {
            line = line.substring(0, line.indexOf("#"));
        }
        return line.trim();
    }

    private boolean isMatch(String line) {
        boolean result = line.length() > 0;

        if (result && !line.matches(".+={1}.+")) {
            throw new IllegalArgumentException();
        }

        return result;
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
