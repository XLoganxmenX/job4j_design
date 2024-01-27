package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder txt = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                txt.append((char) read);
            }

            String[] lines = txt.toString().split(System.lineSeparator());
            for (String line : lines) {
                int num = Integer.parseInt(String.valueOf(line));
                if (num % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
