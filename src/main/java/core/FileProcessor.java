package core;

import config.Config;
import output.ReportPrinter;

import java.io.*;
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
                ReportPrinter.printFileError(file);
                continue;
            }
            try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                char[] readSymbols = new char[3072];
                int count = reader.read(readSymbols);
                StringBuilder text = new StringBuilder();
                while (count > 0) {
                    text.append(readSymbols, 0, count);
                    count = reader.read(readSymbols);
                }
                parseStringsFromFile(text.toString().split("\\R"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseStringsFromFile(String[] stringsFromFile) {
        for (String value : stringsFromFile) {
            if (isInteger(value)) {
                long integerValue = Long.parseLong(value);
                storage.addInteger(integerValue);
                statistic.addInteger(integerValue, config.isFullStatMode(), config.isShortStatMode());
            } else if (isDouble(value)) {
                double floatValue = Double.parseDouble(value);
                storage.addFloatNumber(floatValue);
                statistic.addFloatNumber(floatValue, config.isFullStatMode(), config.isShortStatMode());
            } else {
                if (value.isEmpty()) {
                    continue;
                }
                storage.addSentence(value);
                statistic.addSentence(value, config.isFullStatMode(), config.isShortStatMode());
            }
        }
    }

    private boolean isInteger(String value) {  // TODO обработать числа с E
        if (value.isEmpty()) return false;
        int i = (value.charAt(0) == '-') ? 1 : 0;
        if (value.length() == 1 && i == 1) {
            return false;
        }
        for (; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isDouble(String value) {  // TODO обработать числа с E
        if (value.isEmpty()) {
            return false;
        }
        int i = 0;
        if (value.charAt(0) == '-') {
            if (value.length() == 1) {
                return false;
            }
            i = 1;
        }
        boolean dotSeen = false;
        boolean digitBeforeDot = false;
        boolean digitAfterDot = false;
        for (; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '.') {
                if (dotSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (Character.isDigit(c)) {
                if (!dotSeen) {
                    digitBeforeDot = true;
                } else {
                    digitAfterDot = true;
                }
            } else {
                return false;
            }
        }
        return dotSeen && digitBeforeDot && digitAfterDot;
    }
}
