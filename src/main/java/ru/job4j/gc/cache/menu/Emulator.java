package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final String CACHE_DIR_TEXT = "Введите путь кэшируемой директории:";
    public static final String ADD_CACHE_TEXT = "Введите название файла:";
    public static final String GET_CACHE_TEXT = "Введите название файла, из которого следует получить кэш:";

    public static final String MENU = """
                Введите 1, чтобы указать кэшируемую директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;
    public static final String EXIT = "Выход из эмулятора";

    public void init(Scanner input) {
        DirFileCache dirFileCache = null;

        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(input.nextLine());
            System.out.println(userChoice);
            if (1 == userChoice) {
                dirFileCache = initDirFileCache(input);
            } else if (2 == userChoice) {
                if (dirFileCache == null) {
                    dirFileCache = initDirFileCache(input);
                }

                System.out.println(ADD_CACHE_TEXT);
                String file = input.nextLine();
                dirFileCache.put(file, dirFileCache.get(file));

            } else if (3 == userChoice) {
                if (dirFileCache == null) {
                    dirFileCache = initDirFileCache(input);
                }

                System.out.println(GET_CACHE_TEXT);
                String file = input.nextLine();
                System.out.println(dirFileCache.get(file));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private DirFileCache initDirFileCache(Scanner input) {
        System.out.println(CACHE_DIR_TEXT);
        return new DirFileCache(input.nextLine());
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        Scanner userInput = new Scanner(System.in);
        emulator.init(userInput);
    }
}
