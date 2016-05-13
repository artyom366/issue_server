package sample.service;

import sample.core.ExponentialDistributionGenerator;
import sample.core.Generator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class PoissonIssue {

    public Issue produce(BigDecimal currentTime, Generator generator) {
        Issue issue = new Issue();
        issue.setType(Issue.type.POISSON);

        BigDecimal generationTime = generator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime.add(generationTime));

        return issue;
    }
}
