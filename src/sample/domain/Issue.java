package sample.domain;


public class Issue {

    private double randomGenerationValue;
    private double randomProcessingValue;
    private double generationTime;
    private double processingStartTime;
    private boolean isAwaiting;
    private type type;

    public enum type {ERLANG, POISSON}

    public double getRandomGenerationValue() {
        return randomGenerationValue;
    }

    public void setRandomGenerationValue(double randomGenerationValue) {
        this.randomGenerationValue = randomGenerationValue;
    }

    public double getRandomProcessingValue() {

        return randomProcessingValue;
    }

    public void setRandomProcessingValue(double randomProcessingValue) {
        this.randomProcessingValue = randomProcessingValue;
    }

    public double getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(double generationTime) {
        this.generationTime = generationTime;
    }

    public double getProcessingStartTime() {
        return processingStartTime;
    }

    public void setProcessingStartTime(double processingStartTime) {
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
