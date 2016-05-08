package sample.service;


import sample.core.ExponentialDistributionGenerator;
import sample.core.NormaDistributionGenerator;
import sample.domain.Issue;

public class ServerService {

    public static Double serve(Issue issue) {

        if (issue.getType().equals(Issue.type.ERLANG)) {
            double time = NormaDistributionGenerator.generate();
            issue.setAwaiting(false);
            return time;

        } else {
            double time = ExponentialDistributionGenerator.generate();
            issue.setAwaiting(false);
            return time;
        }
    }
}
