package sample.core;

import sample.system.Reference;

public class ErlangFlowGenerator {

    public static double generate() {
        double sum = 0d;

        for (int i = 0; i < Reference.ERLANG_SUM; i++) {
            double value = ExponentialDistributionGenerator.generate();
            sum += value;
        }

        return sum;
    }
}
