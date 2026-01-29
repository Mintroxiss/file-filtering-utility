public class Main {

    public static void main(String[] args) {
        Config config = Parser.parse(args);
        ConfigValidator.validate(config);
        FileProcessor processor = new FileProcessor(config);
        processor.run();
    }
}
