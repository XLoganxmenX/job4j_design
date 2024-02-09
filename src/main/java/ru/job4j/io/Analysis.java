package ru.job4j.io;

import java.io.*;

public class Analysis {

    private boolean isSrvOnline = true;
    public void unavailable(String source, String target) {


        try (BufferedReader input = new BufferedReader(new FileReader(source));
                PrintWriter output = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(target)))) {
            input.lines().forEach(line -> printShutdownTime(line, output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printShutdownTime(String line, PrintWriter output) {
        String[] word = line.split(" ", 2);

        if (isSrvOnline && ("400".equals(word[0]) || "500".equals(word[0]))) {
            isSrvOnline = false;
            output.append(word[1]).append(";");
        }

        if (!isSrvOnline && ("200".equals(word[0]) || "300".equals(word[0]))) {
            isSrvOnline = true;
            output.append(word[1]).append(";").append(System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
        analysis.unavailable("data/server2.log", "data/target2.csv");
    }
}
