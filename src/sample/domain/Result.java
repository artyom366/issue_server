package sample.domain;


import sample.system.Reference;

import java.math.BigDecimal;
import java.util.List;

public class Result {

    private int event;
    private Issue.type issueType;
    private BigDecimal currentTime;
    private boolean isAvailableServer;
    private BigDecimal poissonIssueGenerationTime;
    private BigDecimal erlangIssueGenerationTime;
    private BigDecimal currentIssueStartProcessingTime;
    private BigDecimal currentIssueProcessingTime;
    private BigDecimal currentIssueEndProcessingTime;
    private int queueLength;
    private List<Issue.type> issuesInQueue;

    private String issuesInQueueScoreView;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public Issue.type getIssueType() {
        return issueType;
    }

    public void setIssueType(Issue.type issueType) {
        this.issueType = issueType;
    }

    public BigDecimal getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(BigDecimal currentTime) {
        this.currentTime = currentTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public boolean getIsAvailableServer() {
        return isAvailableServer;
    }

    public void setIsAvailableServer(boolean availableServer) {
        this.isAvailableServer = availableServer;
    }

    public BigDecimal getPoissonIssueGenerationTime() {
        return poissonIssueGenerationTime;
    }

    private void setPoissonIssueGenerationTime(BigDecimal poissonIssueGenerationTime) {
        this.poissonIssueGenerationTime = poissonIssueGenerationTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getErlangIssueGenerationTime() {
        return erlangIssueGenerationTime;
    }

    private void setErlangIssueGenerationTime(BigDecimal erlangIssueGenerationTime) {
        this.erlangIssueGenerationTime = erlangIssueGenerationTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public void setCurrentIssueGenerationTime(Issue current) {

        if (Issue.type.POISSON.equals(current.getType())) {
            setPoissonIssueGenerationTime(current.getGenerationTime().setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP));
            setErlangIssueGenerationTime(BigDecimal.ZERO);
        } else if (Issue.type.ERLANG.equals(current.getType())) {
            setErlangIssueGenerationTime(current.getGenerationTime().setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP));
            setPoissonIssueGenerationTime(BigDecimal.ZERO);
        } else {
            setErlangIssueGenerationTime(BigDecimal.ZERO);
            setPoissonIssueGenerationTime(BigDecimal.ZERO);
        }
    }

    public BigDecimal getCurrentIssueStartProcessingTime() {
        return currentIssueStartProcessingTime;
    }

    public void setCurrentIssueStartProcessingTime(BigDecimal currentIssueStartProcessingTime) {
        this.currentIssueStartProcessingTime = currentIssueStartProcessingTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCurrentIssueProcessingTime() {
        return currentIssueProcessingTime;
    }

    public void setCurrentIssueProcessingTime(BigDecimal currentIssueProcessingTime) {
        this.currentIssueProcessingTime = currentIssueProcessingTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCurrentIssueEndProcessingTime() {
        return currentIssueEndProcessingTime;
    }

    public void setCurrentIssueEndProcessingTime(BigDecimal currentIssueEndProcessingTime) {
        this.currentIssueEndProcessingTime = currentIssueEndProcessingTime.setScale(Reference.ROUNDING_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public List<Issue.type> getIssuesInQueue() {
        return issuesInQueue;
    }

    public void setIssuesInQueue(List<Issue.type> issuesInQueue) {
        this.issuesInQueue = issuesInQueue;

        int erlang = 0, poisson = 0;
        for (Issue.type type : issuesInQueue) {
            if (type.equals(Issue.type.ERLANG)) {
                erlang++;
            } else {
                poisson++;
            }
        }

        setIssuesInQueueScoreView("Erlang: " + erlang + " Poisson: " + poisson);
    }

    public String getIssuesInQueueScoreView() {
        return issuesInQueueScoreView;
    }

    private void setIssuesInQueueScoreView(String issuesInQueueScoreView) {
        this.issuesInQueueScoreView = issuesInQueueScoreView;
    }
}
