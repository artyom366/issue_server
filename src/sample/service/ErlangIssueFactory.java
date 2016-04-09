package sample.service;


import sample.core.ErlangFlowGenerator;
import sample.domain.Issue;

public class ErlangIssueFactory {

    public static Issue getInstance() {
        Issue issue = new Issue();
        issue.setType(Issue.type.ERLANG);

        double time = ErlangFlowGenerator.generate();
        issue.setGenerationTime(time);

        return issue;
    }
}
