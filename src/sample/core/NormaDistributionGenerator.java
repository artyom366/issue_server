package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;
import java.util.Random;

public class NormaDistributionGenerator {

    public static BigDecimal generate() {
        Random random = new Random();
        return BigDecimal.valueOf(random.nextGaussian() * Reference.STANDARD_DEVIATION + Reference.MEAN);
    }
}
