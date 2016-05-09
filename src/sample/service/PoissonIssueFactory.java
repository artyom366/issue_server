package sample.service;

import sample.core.ExponentialDistributionGenerator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class PoissonIssueFactory {

    public static Issue getInstance(BigDecimal currentTime) {
        Issue issue = new Issue();
        issue.setType(Issue.type.POISSON);

        BigDecimal generationTime = ExponentialDistributionGenerator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime.add(generationTime));

        return issue;
    }
}
