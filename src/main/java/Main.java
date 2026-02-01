import config.ArgParser;
import config.Config;
import config.ConfigValidator;
import core.DataStorage;
import core.FileProcessor;
import core.ResultFileWriter;
import core.Statistic;
import output.ReportPrinter;

public class Main {

    public static void main(String[] args) {
        ReportPrinter printer = new ReportPrinter(System.out);
        Config config = ArgParser.parse(args, printer);
        try {
            ConfigValidator.validate(config);
        } catch (IllegalArgumentException e) {
            printer.illegalArgumentsError(e.getMessage());
            return;
        }
        DataStorage storage = new DataStorage();
        Statistic statistic = new Statistic();
        FileProcessor processor = new FileProcessor(config, storage, statistic);
        processor.run(printer);
        printer.statistics(config, statistic);
        ResultFileWriter fileWriter = new ResultFileWriter(config, storage, printer);
        fileWriter.run();
    }
}
