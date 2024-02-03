package ru.job4j.io;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream output = new BufferedOutputStream(
                     new FileOutputStream("data/output.txt", true))
        ) {
            output.write(input.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
