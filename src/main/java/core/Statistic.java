package core;

public class Statistic {
    private int sentenceCounter;
    private boolean hasSentences;
    private int sentenceMinLength, sentenceMaxLength;

    private int integerCounter;
    private boolean hasIntegers;
    private long integerMin;
    private long integerMax;
    private long integerSum;

    private int floatCounter;
    private boolean hasFloats;
    private double floatMin;
    private double floatMax;
    private double floatSum;

    public int getSentenceCounter() {
        return sentenceCounter;
    }

    public boolean isHasSentences() {
        return hasSentences;
    }

    public int getSentenceMinLength() {
        return sentenceMinLength;
    }

    public int getSentenceMaxLength() {
        return sentenceMaxLength;
    }

    public int getIntegerCounter() {
        return integerCounter;
    }

    public boolean isHasIntegers() {
        return hasIntegers;
    }

    public long getIntegerMin() {
        return integerMin;
    }

    public long getIntegerMax() {
        return integerMax;
    }

    public long getIntegerSum() {
        return integerSum;
    }

    public double getIntegerAverage() {
        return (integerCounter == 0) ? 0 : (double) integerSum / integerCounter;
    }

    public int getFloatCounter() {
        return floatCounter;
    }

    public boolean isHasFloats() {
        return hasFloats;
    }

    public double getFloatMin() {
        return floatMin;
    }

    public double getFloatMax() {
        return floatMax;
    }

    public double getFloatSum() {
        return floatSum;
    }

    public double getFloatAverage() {
        return (floatCounter == 0) ? 0 : floatSum / floatCounter;
    }

    public void setMinMaxIntegerValues(long value) {
        if (!hasIntegers) {
            integerMin = value;
            integerMax = value;
            hasIntegers = true;
        } else {
            integerMin = Math.min(integerMin, value);
            integerMax = Math.max(integerMax, value);
        }
    }

    public void setMinMaxFloatNumValues(double value) {
        if (!hasFloats) {
            floatMin = value;
            floatMax = value;
            hasFloats = true;
        } else {
            floatMin = Math.min(floatMin, value);
            floatMax = Math.max(floatMax, value);
        }
    }

    private void setMinMaxSentenceLengthValues(int value) {
        if (!hasSentences) {
            sentenceMinLength = value;
            sentenceMaxLength = value;
            hasSentences = true;
        } else {
            sentenceMinLength = Math.min(sentenceMinLength, value);
            sentenceMaxLength = Math.max(sentenceMaxLength, value);
        }
    }

    public void addInteger(long value, boolean fullStatMode, boolean shortStatMode) {
        if (shortStatMode || fullStatMode) {
            integerCounter++;
        }
        if (fullStatMode) {
            setMinMaxIntegerValues(value);
            integerSum += value;  // TODO поработать с переполнением
        }
    }

    public void addFloatNumber(Double value, boolean fullStatMode, boolean shortStatMode) {
        if (shortStatMode || fullStatMode) {
            floatCounter++;
        }
        if (fullStatMode) {
            setMinMaxFloatNumValues(value);
            floatSum += value;  // TODO поработать с переполнением
        }
    }

    public void addSentence(String value, boolean fullStatMode, boolean shortStatMode) {
        if (shortStatMode || fullStatMode) {
            sentenceCounter++;
        }
        if (fullStatMode) {
            setMinMaxSentenceLengthValues(value.length());
        }
    }
}
