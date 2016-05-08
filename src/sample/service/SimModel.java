package sample.service;

import sample.domain.Issue;
import sample.domain.Result;
import sample.domain.Server;
import sample.system.Reference;

import java.util.*;

public class SimModel {

    private static List<Issue> erlangList;
    private static List<Issue> poissonList;
    private static Queue<Issue> timeLine;

    private static Queue<Issue> queue = new LinkedList<>();
    private static Server server = new Server();

    private static List<Result> results = new ArrayList<>();

    private static double currentTime = 0d;

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
        result.setCurrentIssueEndProcessingTime(Reference.MODEL_TIME);
        result.setAvailableServer(true);
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

            if (server.isAvailable()) {
                result.setAvailableServer(server.isAvailable());

                server.setAvailable(false);

                if (queue.peek() != null) {
                    Issue issueOnServer = queue.remove();
                    server.setIssueOnServer(issueOnServer);
                    issueOnServer.setProcessingStartTime(currentTime);

                    if (currentTime > (issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime())) {
                        server.setAvailable(true);

                        results.get(issueOnServer.getNumber()).setCurrentIssueEndProcessingTime(issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime());
                    }
                }

            } else {
                result.setAvailableServer(server.isAvailable());

                Issue issueOnServer = server.getIssueOnServer();
                if (currentTime > (issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime())) {
                    server.setAvailable(true);

                    results.get(issueOnServer.getNumber()).setCurrentIssueEndProcessingTime(issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime());
                }
            }

            results.add(result);
            loopCounter++;

        } while (currentTime < Reference.MODEL_TIME);

        return results;
    }


    private static List<Issue> makeErlangIssues() {

        List<Issue> erlangList = new ArrayList<>();
        double time = 0d;

        do {

            Issue erlang = ErlangIssueFactory.getInstance(time);

            double serveTime = ServerService.serve(erlang);

            time = time + erlang.getRandomGenerationValue();
            erlang.setRandomProcessingValue(serveTime);

            erlangList.add(erlang);

        } while (time < Reference.MODEL_TIME);

        return erlangList;
    }

    private static List<Issue> makePoissonList() {

        List<Issue> poissonList = new ArrayList<>();
        double time = 0d;

        do {

            Issue poisson = PoissonIssueFactory.getInstance(time);

            double serveTime = ServerService.serve(poisson);

            time = time + poisson.getRandomGenerationValue();
            poisson.setRandomProcessingValue(serveTime);

            poissonList.add(poisson);

        } while (time < Reference.MODEL_TIME);

        return poissonList;
    }

    private static Queue<Issue> makeTimeLine() {

        List<Issue> sorted = new ArrayList<>();
        sorted.addAll(poissonList);
        sorted.addAll(erlangList);

        Comparator<Issue> comparator = new Comparator<Issue>() {
            @Override
            public int compare(Issue o1, Issue o2) {
                if (o1.getGenerationTime() > o2.getGenerationTime()) {
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
