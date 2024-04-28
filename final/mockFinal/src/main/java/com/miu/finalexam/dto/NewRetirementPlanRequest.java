package com.miu.finalexam.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewRetirementPlanRequest(
        @NotBlank(message = "firstName should not be blank.")
        String firstName,
        @NotBlank(message = "lastName should not be blank.")
        String lastName,
        BigDecimal yearlySalary,
        String referenceNumber,
        LocalDate enrollmentDate,
        LocalDate retirementDate,
        BigDecimal monthlyContribution
) {
}
