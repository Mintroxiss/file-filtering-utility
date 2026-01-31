package core;

public enum ElementType {
    INTEGER("integers.txt"), FLOAT_NUMBER("floats.txt"), SENTENCE("strings.txt");

    private final String fileName;

    ElementType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
