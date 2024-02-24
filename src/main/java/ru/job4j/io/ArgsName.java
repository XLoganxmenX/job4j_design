package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }

        return values.get(key);
    }

    private void parse(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }

        Arrays.stream(args).forEach(arg -> {
            validate(arg);
            String[] parsedArg = arg.substring(1).split("=", 2);
            values.put(parsedArg[0], parsedArg[1]);
        });
    }

    private void validate(String arg) {
        if (arg.length() == 0) {
            throw new IllegalArgumentException(String.format("Arg %s is null. Enter correct arg", arg));
        }

        if (!Pattern.matches("^(?!-=)^-+\\S+={1}\\S+", arg)) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", arg));
            }

            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }

            if (Pattern.matches("^-=.*", arg)) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", arg));
            }

            if (!Pattern.matches("={1}\\S+", arg)) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", arg));
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
