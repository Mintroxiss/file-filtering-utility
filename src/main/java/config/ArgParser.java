package config;

import output.ReportPrinter;

public class ArgParser {
    public static Config parse(String[] args) {
        Config conf = new Config();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> {
                    if (i + 1 < args.length) {
                        conf.setPath(args[i + 1]);
                        i++;
                    } else {
                        ReportPrinter.optionRequiresError(args[i], "пути для результатов");
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length) {
                        conf.setNamePrefix(args[i + 1]);
                        i++;
                    } else {
                        ReportPrinter.optionRequiresError(args[i], "префикса имён выходных файлов");
                    }
                }
                case "-a" -> conf.setAddMode(true);
                case "-s" -> conf.setShortStatMode(true);
                case "-f" -> conf.setFullStatMode(true);
                default -> conf.getFiles().add(args[i]);
            }
        }
        return conf;
    }


}
