package sample.domain;


import java.math.BigDecimal;
import java.util.List;

public class GeneralResults {

    private int poissonCounter;
    private int erlangCounter;
    private int processedIssueCount;
    private BigDecimal averageTimeInQueue;
    private BigDecimal allTimeInQueue;
    private BigDecimal serverStartTime;
    private BigDecimal serverLoad;
    private List<Result> results;

    public void setPoissonCounter(int poissonCounter) {
        this.poissonCounter = poissonCounter;
    }

    public void setErlangCounter(int erlangCounter) {
        this.erlangCounter = erlangCounter;
    }

    public int getPoissonCounter() {
        return poissonCounter;
    }

    public int getErlangCounter() {
        return erlangCounter;
    }

    public int getProcessedIssueCount() {
        return processedIssueCount;
    }

    public void setProcessedIssueCount(int processedIssueCount) {
        this.processedIssueCount = processedIssueCount;
    }

    public BigDecimal getAverageTimeInQueue() {
        return averageTimeInQueue;
    }

    public void setAverageTimeInQueue(BigDecimal averageTimeInQueue) {
        this.averageTimeInQueue = averageTimeInQueue;
    }

    public BigDecimal getAllTimeInQueue() {
        return allTimeInQueue;
    }

    public void setAllTimeInQueue(BigDecimal allTimeInQueue) {
        this.allTimeInQueue = allTimeInQueue;
    }

    public BigDecimal getServerStartTime() {
        return serverStartTime;
    }

    public void setServerStartTime(BigDecimal serverStartTime) {
        this.serverStartTime = serverStartTime;
    }

    public BigDecimal getServerLoad() {
        return serverLoad;
    }

    public void setServerLoad(BigDecimal serverLoad) {
        this.serverLoad = serverLoad;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}


