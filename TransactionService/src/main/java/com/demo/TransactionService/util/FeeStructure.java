package com.demo.TransactionService.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component // Spring component to manage bean creation
public class FeeStructure {

    // Example fee rates per grade level
    private static final Map<Integer, BigDecimal> GRADE_TO_FEE_MAP = new HashMap<>();

    static {
        GRADE_TO_FEE_MAP.put(10, new BigDecimal("10000.00"));
        GRADE_TO_FEE_MAP.put(12, new BigDecimal("12000.00"));

        // Add more grade levels and fees as needed
    }

    public BigDecimal getFeeForGrade(int gradeLevel) {
        return GRADE_TO_FEE_MAP.getOrDefault(gradeLevel, BigDecimal.ZERO);
    }
}
