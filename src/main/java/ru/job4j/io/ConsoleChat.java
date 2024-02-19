package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean isContinue = true;
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String userInput = "";
        String answer = "";
        List<String> wordsFromConsole = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (!OUT.equals(userInput)) {
            List<String> wordsFromFile = readPhrases();
            userInput = scanner.nextLine();
            wordsFromConsole.add(userInput);

            if (checkContinue(userInput)) {
                answer = wordsFromFile.get(random.nextInt(wordsFromFile.size()));
                wordsFromConsole.add(answer);
                System.out.println(answer);
            }
        }

        saveLog(wordsFromConsole);
    }

    private boolean checkContinue(String flag) {
        if (STOP.equals(flag) || OUT.equals(flag)) {
            isContinue = false;
        } else if (CONTINUE.equals(flag)) {
            isContinue = true;
        }
        return isContinue;
    }

    private List<String> readPhrases() {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(words::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8)))) {
            for (String line : log) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./Data/chat.txt", "./Data/randomWords.txt");
        consoleChat.run();
    }
}
