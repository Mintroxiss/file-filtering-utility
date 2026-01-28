import java.util.List;

public class Parser {
    public static Config parse(String[] args) {
        Config conf = new Config();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> i = addNextArgOrError(
                        args,
                        i,
                        conf.getPaths(),
                        "пути для результатов"
                );
                case "-p" -> i = addNextArgOrError(
                        args,
                        i,
                        conf.getNamePrefixes(),
                        "префикса имён выходных файлов"
                );
                case "-a" -> conf.setAddMode(true);
                case "-s" -> conf.setShortStatMode(true);
                case "-f" -> conf.setFullStatMode(true);
                default -> conf.getFiles().add(args[i]);
            }
        }

        return conf;
    }

    private static int addNextArgOrError(String[] args, int i, List<String> list, String requirement) {
        if (i + 1 < args.length) {
            list.add(args[i + 1]);
            return i + 1;
        } else {
            collectErrorMessage(args[i], requirement);
            return i;
        }
    }

    private static void collectErrorMessage(String option, String requirement) {
        System.out.println("Опция \"" + option + "\" требует указания " + requirement);
    }
}
