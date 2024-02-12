package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);

        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1].toLowerCase())).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }

        if (args[0].length() == 0) {
            throw new IllegalArgumentException("Root folder is null. Enter correct path.");
        }

        Path start = Paths.get(args[0]);
        if (!Files.exists(start)) {
            throw new IllegalArgumentException("File/directory not exist. Enter correct path.");
        }

        if (args[1].length() == 0 || !Pattern.matches("[a-zA-Z\\d]+", args[1])) {
            throw new IllegalArgumentException("Enter correct file format.");
        }
    }
}