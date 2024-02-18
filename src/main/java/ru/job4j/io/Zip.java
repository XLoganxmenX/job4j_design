package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Path root;

    public void setRoot(Path root) {
        this.root = root;
    }

    public void pack(Path dir, String exclude, Path out) {
        try {
            packFiles(Search.search(dir, p -> !p.getFileName().toString().contains(exclude)), out.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                String relativePath = root.relativize(path).toString();
                zip.putNextEntry(new ZipEntry(relativePath));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void argsCheck(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        } else if (args.length != 3) {
            throw new IllegalArgumentException("Arguments must be 3");
        }

        for (String arg : args) {
            if (arg.length() == 0) {
                throw new IllegalArgumentException(String.format("Arg %s is null. Enter correct arg.", arg));
            }
        }
    }

    private void argValidate(String path, String exclude, String out) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("File/directory not exist. Enter correct path.");
        }

        if (!Pattern.matches("^.[a-zA-Z\\d]+", exclude)) {
            throw new IllegalArgumentException("Invalid file format");
        }

        if (!out.contains(".zip")) {
            throw new IllegalArgumentException("File don't have zip format");
        } else if (!Pattern.matches("[a-zA-Z\\d]+.zip", out)) {
            throw new IllegalArgumentException("Enter correct file format.");
        }
    }


    public static void main(String[] args) {
        Zip zip = new Zip();

        zip.argsCheck(args);

        ArgsName inArgs = ArgsName.of(args);
        String dir = inArgs.get("d");
        String exclude = inArgs.get("e");
        String out = inArgs.get("o");

        zip.argValidate(dir, exclude, out);

        Path pathDir = Path.of(dir);
        zip.setRoot(pathDir);
        zip.pack(pathDir, exclude, Path.of(out));
    }
}
