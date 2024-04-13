package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path filePath = Paths.get(cachingDir, key);
        StringBuilder stringFromOutput = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile(), StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> {
                stringFromOutput.append(line);
                stringFromOutput.append(System.lineSeparator());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringFromOutput.toString();
    }
}
