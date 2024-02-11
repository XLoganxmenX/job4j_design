package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {

    public void printDuplicates(Path path) throws IOException {
        Map<FileProperty, Set<Path>> duplicatesMap = getDuplicatesInDir(path);
        for (FileProperty file : duplicatesMap.keySet()) {
            if (duplicatesMap.get(file).size() > 1) {
                System.out.println(file);
                duplicatesMap.get(file).forEach(p -> System.out.printf("\t%s%s", p, System.lineSeparator()));
                System.out.println();
            }
        }
    }

    private Map<FileProperty, Set<Path>> getDuplicatesInDir(Path path) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree((path), visitor);
        return visitor.getFileMap();
    }

    public static void main(String[] args) throws IOException {
        Path directory = Path.of("./");
        DuplicatesFinder finder = new DuplicatesFinder();
        finder.printDuplicates(directory);
    }
}