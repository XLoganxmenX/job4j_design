package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {

    private static void argsCheck(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }

        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments must be 4");
        }
    }

    public static void validate(String path, String delimiter, String out, String filter) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("File/directory of path not exist. Enter correct path");
        }

        if (!Pattern.matches(".+.csv", path)) {
            throw new IllegalArgumentException("Invalid file format of path");
        }

        if (!Pattern.matches("\\W{1}", delimiter)) {
            throw new IllegalArgumentException("Invalid format of delimiter");
        }

        if (!(Pattern.matches(".+.csv", out)) && !("stdout".equals(out))) {
            throw new IllegalArgumentException("Invalid file format of out");
        }

        if (!Pattern.matches("\\S+", filter)) {
            throw new IllegalArgumentException("Invalid format of filter");
        }
    }

    private static List<String> parseFilter(String filter) {
        return new ArrayList<>(Arrays.asList(filter.split(",")));
    }

    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> linesToPrint = new LinkedList<>();

        validate(path, delimiter, out, filter);


        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            scanner.useDelimiter(System.lineSeparator());

            List<String> firstColumns = getFirstLineColumns(scanner, delimiter);
            List<Integer> indexes = getColumnIndexes(firstColumns, filter);

            addToPrint(firstColumns, indexes, delimiter, linesToPrint);
            extractColumns(scanner, indexes, delimiter, linesToPrint);

            if ("stdout".equals(out)) {
                linesToPrint.forEach(System.out::print);
            } else {
                saveToFile(linesToPrint, out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractColumns(Scanner scanner, List<Integer> indexes,
                                       String delimiter, List<String> linesToPrint) {
        while (scanner.hasNext()) {
            List<String> columnsInLine = new ArrayList<>();
            Scanner wordScanner = new Scanner(scanner.nextLine());
            wordScanner.useDelimiter(delimiter);

            while (wordScanner.hasNext()) {
                columnsInLine.add(wordScanner.next());
            }

            addToPrint(columnsInLine, indexes, delimiter, linesToPrint);
        }
    }

    private static List<Integer> getColumnIndexes(List<String> columns, String filter) {
        List<Integer> indexes = new LinkedList<>();
        List<String> filterList = parseFilter(filter);

        for (String fltr : filterList) {
            int index = columns.indexOf(fltr);
            if (index != -1) {
                indexes.add(index);
            }
        }

        return indexes;
    }

    private static List<String> getFirstLineColumns(Scanner scanner, String delimiter) {
        List<String> columns = new ArrayList<>();
        Scanner firstLineScanner = new Scanner(scanner.nextLine());
        firstLineScanner.useDelimiter(delimiter);

        while (firstLineScanner.hasNext()) {
            String word = firstLineScanner.next();
            columns.add(word);
        }

        return columns;
    }

    private static void addToPrint(List<String> list, List<Integer> indexes,
                                   String delimiter, List<String> linesToPrint) {
        StringBuilder columnStringBuilder = new StringBuilder();

        for (Integer index : indexes) {
            columnStringBuilder.append(list.get(index)).append(delimiter);
        }
        columnStringBuilder.deleteCharAt(columnStringBuilder.length() - 1);
        linesToPrint.add(columnStringBuilder.append(System.lineSeparator()).toString());
    }

    private static void saveToFile(List<String> lines, String out) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(out, StandardCharsets.UTF_8)))) {
            lines.forEach(writer::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        argsCheck(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

