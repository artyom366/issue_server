package sample.service;


import sample.core.ErlangFlowGenerator;
import sample.core.ExponentialDistributionGenerator;
import sample.domain.Issue;

public class PoissonIssueFactory {

    public static Issue getInstance() {
        Issue issue = new Issue();
        issue.setType(Issue.type.POISSON);

        double time = ExponentialDistributionGenerator.generate();
        issue.setGenerationTime(time);

        return issue;
    }
}
