package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class CommentGenerator implements Generate {

    private static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    private static final String SEPARATOR = System.lineSeparator();
    private List<Comment> comments = new ArrayList<>();
    private static final int COUNT = 50;
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        StringJoiner joiner = new StringJoiner(SEPARATOR);
        for (int i = 0; i < COUNT; i++) {
            joiner.setEmptyValue(SEPARATOR);
            joiner.add(phrases.get(random.nextInt(phrases.size())))
                    .add(phrases.get(random.nextInt(phrases.size())))
                    .add(phrases.get(random.nextInt(phrases.size())));

            comments.add(new Comment(joiner.toString(),
                    userGenerator.randomUser()));
        }
    }
}