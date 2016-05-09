package sample.domain;


import java.math.BigDecimal;

public class Issue {

    private int number;
    private BigDecimal randomGenerationValue;
    private BigDecimal randomProcessingValue;
    private BigDecimal generationTime;
    private BigDecimal processingStartTime;
    private boolean isAwaiting;
    private type type;

    public enum type {ERLANG, POISSON, NO_ISSUE}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getRandomGenerationValue() {
        return randomGenerationValue;
    }

    public void setRandomGenerationValue(BigDecimal randomGenerationValue) {
        this.randomGenerationValue = randomGenerationValue;
    }

    public BigDecimal getRandomProcessingValue() {
        return randomProcessingValue;
    }

    public void setRandomProcessingValue(BigDecimal randomProcessingValue) {
        this.randomProcessingValue = randomProcessingValue;
    }

    public BigDecimal getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(BigDecimal generationTime) {
        this.generationTime = generationTime;
    }

    public BigDecimal getProcessingStartTime() {
        return processingStartTime;
    }

    public void setProcessingStartTime(BigDecimal processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public boolean isAwaiting() {
        return isAwaiting;
    }

    public void setAwaiting(boolean awaiting) {
        isAwaiting = awaiting;
    }

    public Issue.type getType() {
        return type;
    }

    public void setType(Issue.type type) {
        this.type = type;
    }
}
