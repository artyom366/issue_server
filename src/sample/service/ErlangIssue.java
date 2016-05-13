package sample.service;


import sample.core.ErlangFlowGenerator;
import sample.core.Generator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class ErlangIssue {

    public Issue produce(BigDecimal currentTime, Generator generator) {
        Issue issue = new Issue();
        issue.setType(Issue.type.ERLANG);

        BigDecimal generationTime = generator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime.add(generationTime));

        return issue;
    }
}
