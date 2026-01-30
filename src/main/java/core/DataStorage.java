package core;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private final List<String> sentences = new ArrayList<>();
    private final List<Long> integers = new ArrayList<>();
    private final List<Double> floatNumbers = new ArrayList<>();

    public List<String> getSentences() {
        return sentences;
    }

    public List<Long> getIntegers() {
        return integers;
    }

    public List<Double> getFloatNumbers() {
        return floatNumbers;
    }

    public void addSentence(String value) {
        sentences.add(value);
    }

    public  void addInteger(long value) {
        integers.add(value);
    }

    public void addFloatNumber(double value) {
        floatNumbers.add(value);
    }
}
