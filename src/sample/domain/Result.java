package sample.domain;


import java.util.List;

public class Result {

    private int event;
    private Issue.type issueType;
    private double currentTime;
    private boolean isAvailableServer;
    private double poissonIssueGenerationTime;
    private double erlangIssueGenerationTime;
    private double currentIssueEndProcessingTime;
    private int queueLength;
    private List<Issue.type> issuesInQueue;

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

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public boolean getIsAvailableServer() {
        return isAvailableServer;
    }

    public void setAvailableServer(boolean availableServer) {
        this.isAvailableServer = availableServer;
    }

    public double getPoissonIssueGenerationTime() {
        return poissonIssueGenerationTime;
    }

    private void setPoissonIssueGenerationTime(double poissonIssueGenerationTime) {
        this.poissonIssueGenerationTime = poissonIssueGenerationTime;
    }

    public double getErlangIssueGenerationTime() {
        return erlangIssueGenerationTime;
    }

    private void setErlangIssueGenerationTime(double erlangIssueGenerationTime) {
        this.erlangIssueGenerationTime = erlangIssueGenerationTime;
    }

    public void setCurrentIssueGenerationTime(Issue current) {

        if (Issue.type.POISSON.equals(current.getType())) {
            setPoissonIssueGenerationTime(current.getGenerationTime());
            setErlangIssueGenerationTime(-1);
        } else if (Issue.type.ERLANG.equals(current.getType())) {
            setErlangIssueGenerationTime(current.getGenerationTime());
            setPoissonIssueGenerationTime(-1);
        } else {
            setErlangIssueGenerationTime(-1);
            setPoissonIssueGenerationTime(-1);
        }
    }

    public double getCurrentIssueEndProcessingTime() {
        return currentIssueEndProcessingTime;
    }

    public void setCurrentIssueEndProcessingTime(double currentIssueEndProcessingTime) {
        this.currentIssueEndProcessingTime = currentIssueEndProcessingTime;
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
    }
}
