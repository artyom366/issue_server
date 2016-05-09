package sample.service;

import sample.domain.Issue;
import sample.domain.Result;
import sample.domain.Server;
import sample.system.Reference;

import java.math.BigDecimal;
import java.util.*;

public class SimModel {

    private static List<Issue> erlangList;
    private static List<Issue> poissonList;
    private static Queue<Issue> timeLine;

    private static Queue<Issue> queue = new LinkedList<>();
    private static Server server = new Server();

    private static List<Result> results = new ArrayList<>();

    private static BigDecimal currentTime = BigDecimal.valueOf(0d);

    public static List<Result> run() {

        erlangList = makeErlangIssues();
        poissonList = makePoissonList();
        timeLine = makeTimeLine();

        server.setAvailable(true);

        Result result = new Result();
        result.setEvent(0);
        result.setIssueType(Issue.type.NO_ISSUE);
        result.setCurrentTime(currentTime);
        result.setCurrentIssueGenerationTime(new Issue());
        result.setCurrentIssueStartProcessingTime(BigDecimal.valueOf(Reference.MODEL_TIME));
        result.setCurrentIssueProcessingTime(BigDecimal.valueOf(Reference.MODEL_TIME));
        result.setCurrentIssueEndProcessingTime(BigDecimal.valueOf(Reference.MODEL_TIME));
        result.setIsAvailableServer(true);
        result.setQueueLength(0);
        result.setIssuesInQueue(Collections.emptyList());
        results.add(result);

        int loopCounter = 1;

        do {
            if (timeLine.isEmpty()) break;

            result = new Result();
            result.setEvent(loopCounter);
            result.setQueueLength(queue.size());

            List<Issue.type> issuesInQueue = new ArrayList<>();
            for (Issue issueInQueue : queue) {
                issuesInQueue.add(issueInQueue.getType());
            }
            result.setIssuesInQueue(issuesInQueue);

            Issue current = timeLine.remove();
            currentTime = current.getGenerationTime();
            queue.add(current);

            result.setIssueType(current.getType());
            result.setCurrentTime(currentTime);
            result.setCurrentIssueGenerationTime(current);
            result.setCurrentIssueProcessingTime(current.getRandomProcessingValue());

            if (server.isAvailable()) {
                result.setIsAvailableServer(server.isAvailable());

                server.setAvailable(false);

                if (queue.peek() != null) {
                    Issue issueOnServer = queue.remove();
                    server.setIssueOnServer(issueOnServer);
                    issueOnServer.setProcessingStartTime(currentTime);

                    if (currentTime.doubleValue() > (issueOnServer.getRandomProcessingValue().add(issueOnServer.getProcessingStartTime()).doubleValue())) {
                        server.setAvailable(true);

                        if (issueOnServer.getNumber() < results.size()) {
                            results.get(issueOnServer.getNumber() + 1).setCurrentIssueEndProcessingTime(issueOnServer.getRandomProcessingValue().add(issueOnServer.getProcessingStartTime()));
                        }
                    }
                }

            } else {
                result.setIsAvailableServer(server.isAvailable());

                Issue issueOnServer = server.getIssueOnServer();

                if (currentTime.doubleValue() > (issueOnServer.getRandomProcessingValue().add( issueOnServer.getProcessingStartTime()).doubleValue())) {
                    server.setAvailable(true);

                    if (issueOnServer.getNumber() < results.size()) {
                        results.get(issueOnServer.getNumber() + 1).setCurrentIssueEndProcessingTime(issueOnServer.getRandomProcessingValue().add(issueOnServer.getProcessingStartTime()));
                    }
                }
            }

            results.add(result);
            loopCounter++;

        } while (currentTime.doubleValue() < Reference.MODEL_TIME);

        return results;
    }


    private static List<Issue> makeErlangIssues() {

        List<Issue> erlangList = new ArrayList<>();
        BigDecimal time = BigDecimal.valueOf(0d);

        do {

            Issue erlang = ErlangIssueFactory.getInstance(time);

            BigDecimal serveTime = ServerService.serve(erlang);

            time = time.add(erlang.getRandomGenerationValue());
            erlang.setRandomProcessingValue(serveTime);

            erlangList.add(erlang);

        } while (time.doubleValue() < Reference.MODEL_TIME);

        return erlangList;
    }

    private static List<Issue> makePoissonList() {

        List<Issue> poissonList = new ArrayList<>();
        BigDecimal time = BigDecimal.valueOf(0d);

        do {

            Issue poisson = PoissonIssueFactory.getInstance(time);

            BigDecimal serveTime = ServerService.serve(poisson);

            time = time.add(poisson.getRandomGenerationValue());
            poisson.setRandomProcessingValue(serveTime);

            poissonList.add(poisson);

        } while (time.doubleValue() < Reference.MODEL_TIME);

        return poissonList;
    }

    private static Queue<Issue> makeTimeLine() {

        List<Issue> sorted = new ArrayList<>();
        sorted.addAll(poissonList);
        sorted.addAll(erlangList);

        Comparator<Issue> comparator = new Comparator<Issue>() {
            @Override
            public int compare(Issue o1, Issue o2) {
                if (o1.getGenerationTime().doubleValue() > o2.getGenerationTime().doubleValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        Collections.sort(sorted, comparator);
        Queue<Issue> queue = new LinkedList<>(sorted);

        int loopCounter = 0;
        for (Issue issue : queue) {
            issue.setNumber(loopCounter);
            loopCounter++;
        }

        return queue;
    }
}
