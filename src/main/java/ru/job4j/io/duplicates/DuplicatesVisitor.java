package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Set<Path>> fileMap = new HashMap<>();

    public Map<FileProperty, Set<Path>> getFileMap() {
        return fileMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty newFile = new FileProperty(Files.size(file), file.getFileName().toString());
            fileMap.computeIfAbsent(newFile, fileProperty -> new HashSet<>()).add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

}
