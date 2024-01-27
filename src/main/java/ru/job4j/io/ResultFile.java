package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            output.write("1 x 1 = 1".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 2 = 2".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 3 = 3".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 4 = 4".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 5 = 5".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 6 = 6".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 7 = 7".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 8 = 8".getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write("1 x 9 = 9".getBytes());
            output.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
