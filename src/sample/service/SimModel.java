package sample.service;

import sample.domain.Issue;
import sample.domain.Server;
import sample.system.Reference;

import java.util.*;

public class SimModel {

    private static List<Issue> erlangList;
    private static List<Issue> poissonList;
    private static Queue<Issue> timeLine;

    private static Queue<Issue> queue = new LinkedList<>();
    private static Server server = new Server();

    private static double currentTime = 0d;

    public static void run() {

        server.setAvailable(true);

        erlangList = makeErlangIssues();
        poissonList = makePoissonList();
        timeLine = makeTimeLine();

        do {
            if (timeLine.isEmpty()) break;


            Issue issue = timeLine.remove();
            currentTime = issue.getGenerationTime();
            queue.add(issue);

            if (server.isAvailable()) {
                server.setAvailable(false);

                if (queue.peek() != null) {
                    Issue issueOnServer = queue.remove();
                    server.setIssueOnServer(issueOnServer);
                    issueOnServer.setProcessingStartTime(currentTime);

                    if (currentTime > (issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime()))
                        server.setAvailable(true);
                }

            } else {
                Issue issueOnServer = server.getIssueOnServer();

                if (currentTime > (issueOnServer.getRandomProcessingValue() + issueOnServer.getProcessingStartTime()))
                    server.setAvailable(true);
            }

        } while (currentTime < Reference.MODEL_TIME);
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
        return new LinkedList<>(sorted);
    }


}
