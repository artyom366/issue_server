package sample.service;


import sample.core.ExponentialDistributionGenerator;
import sample.core.NormaDistributionGenerator;
import sample.domain.Issue;

import java.math.BigDecimal;

public class ServerService {

    public static BigDecimal serve(Issue issue) {

        if (issue.getType().equals(Issue.type.ERLANG)) {
            BigDecimal time = NormaDistributionGenerator.generate();
            issue.setAwaiting(false);
            return time;

        } else {
            BigDecimal time = ExponentialDistributionGenerator.generate();
            issue.setAwaiting(false);
            return time;
        }
    }
}
