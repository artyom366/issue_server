package sample.domain;


public class Server {

    private boolean isAvailable;
    private int counter;
    private Issue issueOnServer;

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Issue getIssueOnServer() {
        return issueOnServer;
    }

    public void setIssueOnServer(Issue issueOnServer) {
        this.issueOnServer = issueOnServer;
    }
}
