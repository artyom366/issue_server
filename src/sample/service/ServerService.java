package sample.service;


import sample.core.Generator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class ServerService {

    public BigDecimal serve(Issue issue,
                            Generator generator) {

        if (issue.getType().equals(Issue.type.ERLANG)) {
            BigDecimal time = generator.generate();
            issue.setAwaiting(false);
            return time;

        } else {
            BigDecimal time = generator.generate();
            issue.setAwaiting(false);
            return time;
        }
    }
}
