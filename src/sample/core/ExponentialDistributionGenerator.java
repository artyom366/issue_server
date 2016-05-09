package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;
import java.util.Random;

public class ExponentialDistributionGenerator {

    public static BigDecimal generate() {
        Random random = new Random();
        return BigDecimal.valueOf(-(1 / Reference.LAMBDA_EXPONENTIAL) * Math.log(1 - random.nextDouble()));
    }
}
