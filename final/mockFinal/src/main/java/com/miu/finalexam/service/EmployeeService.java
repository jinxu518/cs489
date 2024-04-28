package com.miu.finalexam.service;


import com.miu.finalexam.dto.EmployeeUpdateRequest;
import com.miu.finalexam.model.Employee;
import com.miu.finalexam.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployeesWithRetirementPlans();
    EmployeeResponse getRetirementPlanByEmployeeId(Long employeeId);
    EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest employeeUpdateRequest);
}
