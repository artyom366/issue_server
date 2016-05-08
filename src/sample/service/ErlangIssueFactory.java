package sample.service;


import sample.core.ErlangFlowGenerator;
import sample.domain.Issue;

public class ErlangIssueFactory {

    public static Issue getInstance(double currentTime) {
        Issue issue = new Issue();
        issue.setType(Issue.type.ERLANG);

        double generationTime = ErlangFlowGenerator.generate();
        issue.setRandomGenerationValue(generationTime);
        issue.setGenerationTime(currentTime + generationTime);

        return issue;
    }
}
