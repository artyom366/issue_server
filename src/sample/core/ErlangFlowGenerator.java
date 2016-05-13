package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;

public class ErlangFlowGenerator implements Generator {

    @Override
    public BigDecimal generate() {
        ExponentialDistributionGenerator exponential = new ExponentialDistributionGenerator();

        BigDecimal sum = BigDecimal.valueOf(0d);

        for (int i = 0; i < Reference.ERLANG_SUM; i++) {
            BigDecimal value = exponential.generate();
            sum = sum.add(value);
        }

        return sum.setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
