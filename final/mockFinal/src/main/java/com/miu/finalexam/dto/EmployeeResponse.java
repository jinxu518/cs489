package com.miu.finalexam.dto;


import java.math.BigDecimal;

public record EmployeeResponse(
        long employeeId,
        String firstName,
        String lastName,
        BigDecimal yearlySalary,
        RetirementPlanResponse retirementPlanResponse

) {
}
