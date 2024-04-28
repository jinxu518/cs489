package com.miu.finalexam.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        BigDecimal yearlySalary,
        String referenceNumber,
        LocalDate enrollmentDate,
        LocalDate retirementDate,
        BigDecimal monthlyContribution
) {
}
