package sample.core;

import sample.system.Reference;

import java.util.Random;

public class NormaDistributionGenerator {

    public static double generate() {
        Random random = new Random();
        return random.nextGaussian() * Reference.STANDARD_DEVIATION + Reference.MEAN;
    }
}
