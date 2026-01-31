package core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistic {
    private static final int SCALE = 40;

    private long sentenceCounter;
    private boolean hasSentences;
    private int sentenceMinLength, sentenceMaxLength;

    private BigDecimal integerCounter = BigDecimal.ZERO;
    private boolean hasIntegers;
    private BigDecimal integerMin = BigDecimal.ZERO;
    private BigDecimal integerMax = BigDecimal.ZERO;
    private BigDecimal integerSum = BigDecimal.ZERO;

    private BigDecimal floatCounter = BigDecimal.ZERO;
    private boolean hasFloats;
    private BigDecimal floatMin = BigDecimal.ZERO;
    private BigDecimal floatMax = BigDecimal.ZERO;
    private BigDecimal floatSum = BigDecimal.ZERO;

    public long getSentenceCounter() {
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

    public BigDecimal getIntegerCounter() {
        return integerCounter;
    }

    public boolean isHasIntegers() {
        return hasIntegers;
    }

    public BigDecimal getIntegerMin() {
        return integerMin;
    }

    public BigDecimal getIntegerMax() {
        return integerMax;
    }

    public BigDecimal getIntegerSum() {
        return integerSum;
    }

    public BigDecimal getIntegerAverage() {
        if (integerCounter.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return integerSum.divide(integerCounter, SCALE, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public BigDecimal getFloatCounter() {
        return floatCounter;
    }

    public boolean isHasFloats() {
        return hasFloats;
    }

    public BigDecimal getFloatMin() {
        return floatMin;
    }

    public BigDecimal getFloatMax() {
        return floatMax;
    }

    public BigDecimal getFloatSum() {
        return floatSum;
    }

    public BigDecimal getFloatAverage() {
        if (floatCounter.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return floatSum.divide(floatCounter, SCALE, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    private void setMinMaxIntegerValues(BigDecimal value) {
        if (!hasIntegers) {
            integerMin = value;
            integerMax = value;
            hasIntegers = true;
        } else {
            if (value.compareTo(integerMin) < 0) {
                integerMin = value;
            }
            if (value.compareTo(integerMax) > 0) {
                integerMax = value;
            }
        }
    }

    private void setMinMaxFloatNumValues(BigDecimal value) {
        if (!hasFloats) {
            floatMin = value;
            floatMax = value;
            hasFloats = true;
        } else {
            if (value.compareTo(floatMin) < 0) {
                floatMin = value;
            }
            if (value.compareTo(floatMax) > 0) {
                floatMax = value;
            }
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

    public void addInteger(BigDecimal value, boolean fullStatMode, boolean shortStatMode) {
        if (shortStatMode || fullStatMode) {
            integerCounter = integerCounter.add(BigDecimal.ONE);
        }
        if (fullStatMode) {
            setMinMaxIntegerValues(value);
            integerSum = integerSum.add(value);
        }
    }

    public void addFloatNumber(BigDecimal value, boolean fullStatMode, boolean shortStatMode) {
        if (shortStatMode || fullStatMode) {
            floatCounter = floatCounter.add(BigDecimal.ONE);
        }
        if (fullStatMode) {
            setMinMaxFloatNumValues(value);
            floatSum = floatSum.add(value);
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
