package sample.service;


import sample.core.ErlangFlowGenerator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class ErlangIssueFactory {

    public static Issue getInstance(BigDecimal currentTime) {
        Issue issue = new Issue();
        issue.setType(Issue.type.ERLANG);

        BigDecimal generationTime = ErlangFlowGenerator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime.add(generationTime));

        return issue;
    }
}
