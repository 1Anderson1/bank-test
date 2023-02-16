package com.example.banktest.utils;

import java.math.BigDecimal;

public class PercentageMathUtil {

    public static BigDecimal getIncreasedByPercentage(BigDecimal value, BigDecimal percentage) {
        return value.add(value.multiply(percentage).divide(new BigDecimal(100)));
    }
}
