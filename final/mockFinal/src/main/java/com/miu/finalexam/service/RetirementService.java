package com.miu.finalexam.service;

import com.miu.finalexam.dto.EmployeeResponse;
import com.miu.finalexam.dto.NewRetirementPlanRequest;

import java.time.LocalDate;
import java.util.List;

public interface RetirementService {
    List<EmployeeResponse> getUpcomingRetirees(LocalDate startDate, LocalDate endDate);
    void addNewRetirementPlan(NewRetirementPlanRequest request);
}
