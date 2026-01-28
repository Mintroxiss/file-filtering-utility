public class Main {

    public static void main(String[] args) {
        Config conf = Parser.parse(args);
        FileProcessor processor = new FileProcessor(conf);
        processor.run();
    }
}
