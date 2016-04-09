package sample.core;

import sample.system.Reference;

import java.util.Random;

public class ExponentialDistributionGenerator {

    public static double generate() {
        Random random = new Random();
        return -(1 / Reference.LAMBDA_EXPONENTIAL) * Math.log(1 - random.nextDouble());
    }
}
