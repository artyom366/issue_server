package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;

public class ErlangFlowGenerator {

    public static BigDecimal generate() {
        BigDecimal sum = BigDecimal.valueOf(0d);

        for (int i = 0; i < Reference.ERLANG_SUM; i++) {
            BigDecimal value = ExponentialDistributionGenerator.generate();
            sum = sum.add(value);
        }

        return sum;
    }
}
