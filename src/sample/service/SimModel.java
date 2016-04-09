package sample.service;


import sample.domain.Issue;
import sample.domain.Server;
import sample.system.Reference;

import java.util.LinkedList;
import java.util.Queue;


public class SimModel {

    public static void run() {

        Double currentTime = 0d;
        Queue<Issue> fifo = new LinkedList<>();
        Server server = new Server();

        do {

            Issue elang = ErlangIssueFactory.getInstance();
            Issue poisson = PoissonIssueFactory.getInstance();



            if (elang.getGenerationTime() > poisson.getGenerationTime()) {

            }

            Issue currentIssue = elang.getGenerationTime() > poisson.getGenerationTime() ? poisson : elang;
            currentTime += currentIssue.getGenerationTime();

            if (server.isAvailable()) {
                double serveTime = ServerService.serve(currentIssue);
                currentTime += serveTime;
            }







        } while (currentTime < Reference.MODEL_TIME);

    }
}
