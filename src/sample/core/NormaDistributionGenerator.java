package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;
import java.util.Random;

public class NormaDistributionGenerator implements Generator {

    @Override
    public BigDecimal generate() {
        Random random = new Random();
        return BigDecimal.valueOf(random.nextGaussian() * Reference.STANDARD_DEVIATION + Reference.MEAN).setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
