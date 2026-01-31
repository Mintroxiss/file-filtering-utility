package core;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private final List<String> sentences = new ArrayList<>();
    private final List<String> integers = new ArrayList<>();
    private final List<String> floatNumbers = new ArrayList<>();

    public List<String> getSentences() {
        return sentences;
    }

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getFloatNumbers() {
        return floatNumbers;
    }

    public void addSentence(String value) {
        sentences.add(value);
    }

    public void addInteger(String value) {
        integers.add(value);
    }

    public void addFloatNumber(String value) {
        floatNumbers.add(value);
    }
}
