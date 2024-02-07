package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> lines;
        List<String> timeList = new ArrayList<>(8);
        boolean isSrvOnline = true;

        try (BufferedReader input = new BufferedReader(new FileReader(source));
                PrintWriter output = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(target)))) {

            lines = input.lines().collect(Collectors.toList());
            for (String line : lines) {
                String[] word = line.split(" ", 2);
                StringBuilder builder = new StringBuilder();

                if (isSrvOnline && ("400".equals(word[0]) || "500".equals(word[0]))) {
                    isSrvOnline = false;
                    builder.append(word[1]).append(";");
                }

                if (!isSrvOnline && ("200".equals(word[0]) || "300".equals(word[0]))) {
                    isSrvOnline = true;
                    builder.append(word[1]).append(";");
                }

                if (!builder.isEmpty()) {
                    timeList.add(builder.toString());
                }
            }

            for (int i = 0; i < timeList.size(); i = i + 2) {
                output.printf(timeList.get(i) + timeList.get(i + 1));
                if (i + 2 < timeList.size()) {
                    output.print(System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
        analysis.unavailable("data/server2.log", "data/target2.csv");
    }
}
