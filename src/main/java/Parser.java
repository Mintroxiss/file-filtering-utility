public class Parser {
    public static Config parse(String[] args) {
        Config conf = new Config();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> {
                    if (i + 1 < args.length) {
                        conf.setPath(args[i + 1]);
                        i++;
                    } else {
                        collectErrorMessage(args[i], "пути для результатов");
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length) {
                        conf.setNamePrefix(args[i + 1]);
                        i++;
                    } else {
                        collectErrorMessage(args[i], "префикса имён выходных файлов");
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

    private static void collectErrorMessage(String option, String requirement) {
        System.out.println("Опция \"" + option + "\" требует указания " + requirement);
    }
}
