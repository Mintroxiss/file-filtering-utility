import config.ArgParser;
import config.Config;
import config.ConfigValidator;
import core.DataStorage;
import core.FileProcessor;
import core.FileWriter;
import core.Statistic;
import output.ReportPrinter;

public class Main {

    public static void main(String[] args) {
        Config config = ArgParser.parse(args);
        try {
            ConfigValidator.validate(config);
        } catch (IllegalArgumentException e) {
            ReportPrinter.illegalArgumentsError(e.getMessage());
            return;
        }
        DataStorage storage = new DataStorage();
        Statistic statistic = new Statistic();
        FileProcessor processor = new FileProcessor(config, storage, statistic);
        processor.run();
        ReportPrinter.statistics(config, statistic);
        FileWriter fileWriter = new FileWriter(config, storage);
        fileWriter.run();
        
    }
}
