package sample.domain;


public class Result {

    private int event;
    private double currentTime;
    private double poissonTime;
    private double erlangTime;
    private double processingTime;
    private double remainingTime;
    private boolean serverStatus;
    private int queueLength;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public double getPoissonTime() {
        return poissonTime;
    }

    public void setPoissonTime(double poissonTime) {
        this.poissonTime = poissonTime;
    }

    public double getErlangTime() {
        return erlangTime;
    }

    public void setErlangTime(double erlangTime) {
        this.erlangTime = erlangTime;
    }

    public double getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(double processingTime) {
        this.processingTime = processingTime;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public boolean isServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    @Override
    public String toString() {
        return "Result{" +
                "event = " + event +
                ", currentTime = " + currentTime +
                ", poissonTime = " + poissonTime +
                ", erlangTime = " + erlangTime +
                ", processingTime = " + processingTime +
                ", remainingTime = " + remainingTime +
                ", serverStatus = " + serverStatus +
                ", queueLength = " + queueLength +
                '}';
    }
}
