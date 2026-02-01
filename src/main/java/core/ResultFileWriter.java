package core;

import config.Config;
import output.ReportPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ResultFileWriter {
    private final Config config;
    private final DataStorage storage;
    private final ReportPrinter printer;

    public ResultFileWriter(Config config, DataStorage storage, ReportPrinter printer) {
        this.config = config;
        this.storage = storage;
        this.printer = printer;
    }

    public void run() {
        if (!createDirectory()) {
            config.setPath(System.getProperty("user.dir"));
        }
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> createAndFillFile(ElementType.SENTENCE, storage.getSentences()));
        executor.submit(() -> createAndFillFile(ElementType.FLOAT_NUMBER, storage.getFloatNumbers()));
        executor.submit(() -> createAndFillFile(ElementType.INTEGER, storage.getIntegers()));
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void createAndFillFile(ElementType name, List<String> elements) {
        if (elements.isEmpty()) {
            return;
        }
        Path path = Paths.get(
                config.getPath(),
                config.getNamePrefix() + name.getFileName()
        );
        File file = path.toFile();
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file, config.isAddMode()),
                        StandardCharsets.UTF_8
                )
        )) {
            if (config.isAddMode() && file.length() > 0) {
                writer.newLine();
            }
            for (String element : elements) {
                writer.write(element);
                writer.newLine();
            }
            printer.filledFileMessage(file);
        } catch (IOException e) {
            printer.writeFileError(file);
        }
    }

    private boolean createDirectory() {
        File directory = new File(config.getPath());
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                printer.notDirectoryError(directory);
                return false;
            }
        } else {
            if (!directory.mkdirs()) {
                printer.createDirectoryError(directory);
                return false;
            }
        }
        return true;
    }
}
