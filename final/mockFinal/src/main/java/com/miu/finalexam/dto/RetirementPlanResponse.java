package com.miu.finalexam.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RetirementPlanResponse(
        long planId,
        String referenceNumber,
        LocalDate enrollmentDate,
        LocalDate retirementDate,
        BigDecimal monthlyContribution

) {
}
