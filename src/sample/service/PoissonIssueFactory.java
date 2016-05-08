package sample.service;

import sample.core.ExponentialDistributionGenerator;
import sample.domain.Issue;

public class PoissonIssueFactory {

    public static Issue getInstance(double currentTime) {
        Issue issue = new Issue();
        issue.setType(Issue.type.POISSON);

        double generationTime = ExponentialDistributionGenerator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime + generationTime);

        return issue;
    }
}
