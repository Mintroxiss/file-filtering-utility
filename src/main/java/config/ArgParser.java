package config;

import output.ReportPrinter;

public class ArgParser {
    public static Config parse(String[] args, ReportPrinter printer) {
        Config conf = new Config();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        conf.setPath(args[i + 1]);
                        i++;
                    } else {
                        printer.optionRequiresError(arg, "пути для результатов");
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        conf.setNamePrefix(args[i + 1]);
                        i++;
                    } else {
                        printer.optionRequiresError(arg, "префикса имён выходных файлов");
                    }
                }
                case "-a" -> conf.setAddMode(true);
                case "-s" -> conf.setShortStatMode(true);
                case "-f" -> conf.setFullStatMode(true);
                default -> {
                    if (arg.startsWith("-")) {
                        printer.unknownOptionError(arg);
                    } else {
                        conf.getFiles().add(arg);
                    }
                }
            }
        }
        return conf;
    }
}

