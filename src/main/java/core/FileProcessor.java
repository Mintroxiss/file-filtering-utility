package core;

import config.Config;
import output.ReportPrinter;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class FileProcessor {
    private final Config config;
    private final DataStorage storage;
    private final Statistic statistic;

    public FileProcessor(Config config, DataStorage storage, Statistic statistic) {
        this.config = config;
        this.storage = storage;
        this.statistic = statistic;
    }

    public void run() {
        for (String pathToFile : config.getFiles()) {
            File file = new File(pathToFile);
            if (!file.isFile() || !file.exists()) {
                ReportPrinter.fileError(file);
                continue;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8))) {
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append(System.lineSeparator());
                }
                parseStringsFromFile(text.toString().split("\\R"));
            } catch (IOException e) {
                ReportPrinter.readFileError(file);
            }
        }
    }

    private void parseStringsFromFile(String[] stringsFromFile) {
        for (String value : stringsFromFile) {
            value = value.trim();
            if (value.isEmpty()) {
                continue;
            }
            BigDecimal numberValue = parseNumber(value);
            if (numberValue != null) {
                BigDecimal normalized = numberValue.stripTrailingZeros();
                if (normalized.scale() <= 0) {
                    storage.addInteger(value);
                    statistic.addInteger(normalized, config.isFullStatMode(), config.isShortStatMode());
                } else {
                    storage.addFloatNumber(value);
                    statistic.addFloatNumber(normalized, config.isFullStatMode(), config.isShortStatMode());
                }
            } else {
                storage.addSentence(value);
                statistic.addSentence(value, config.isFullStatMode(), config.isShortStatMode());
            }
        }
    }

    private BigDecimal parseNumber(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
