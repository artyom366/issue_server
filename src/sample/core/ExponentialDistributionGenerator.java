package sample.core;

import sample.system.Reference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ExponentialDistributionGenerator implements Generator {

    @Override
    public BigDecimal generate() {
        Random random = new Random();
        return BigDecimal.valueOf(-(1 / Reference.LAMBDA_EXPONENTIAL) * Math.log(1 - random.nextDouble())).setScale(2, RoundingMode.HALF_UP).setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
