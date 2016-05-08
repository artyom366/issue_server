package sample.service;

import sample.domain.Issue;
import sample.domain.Result;
import sample.domain.Server;
import sample.system.Reference;

import java.util.*;

public class SimModel {

    private static Queue<Issue> queue = new LinkedList<>();
    private static Server server = new Server();

    private static double currentTime = 0d;
    private static double futureTime = 0d;

    private static int erlangMarker = 0;
    private static int poissonMarker = 0;

    private static List<Issue> erlangList;
    private static List<Issue> poissonList;

    private static int eventCounter = 0;
    private static List<Result> results = new ArrayList<>();
    private static Result result = new Result();

    public static void run() {

        erlangList = makeErlangIssues();
        poissonList = makePoissonList();

        server.setIsAvailable(true);

        getIssues();
    }

    private static void getIssues() {
        if (erlangList.get(erlangMarker).getGenerationTime() > poissonList.get(poissonMarker).getGenerationTime()) {
            run(poissonList.get(poissonMarker), erlangList.get(erlangMarker));
        } else {
            run(erlangList.get(erlangMarker), poissonList.get(poissonMarker));
        }
    }

    private static void run(Issue next, Issue following) {

        eventCounter++;
        result.setEvent(eventCounter);

        if (futureTime < next.getGenerationTime()) {

            currentTime = currentTime + next.getRandomGenerationValue();
            queue.add(next);

            if (server.isAvailable()) {

                server.setIsAvailable(false);

                Issue current;
                try {
                    current = queue.remove();
                } catch (NoSuchElementException e) {
                    return;
                }

                futureTime = currentTime + current.getRandomProcessingValue();

                if (following.getGenerationTime() < currentTime) {
                    queue.add(following);
                }

            } else {
                futureTime = currentTime;
                server.setIsAvailable(true);
                queue.add(next);
            }

        } else {
            currentTime = futureTime;
            server.setIsAvailable(true);
            queue.add(next);
        }

        if (next.getType().equals(Issue.type.POISSON)) {
            poissonMarker++;

        } else {
            erlangMarker++;
        }

        getIssues();
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


}
