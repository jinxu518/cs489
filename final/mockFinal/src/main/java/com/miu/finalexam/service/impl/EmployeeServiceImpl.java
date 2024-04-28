package com.miu.finalexam.service.impl;

import com.miu.finalexam.dto.EmployeeUpdateRequest;
import com.miu.finalexam.dto.RetirementPlanResponse;
import com.miu.finalexam.exception.ResourceNotFoundException;
import com.miu.finalexam.model.Employee;
import com.miu.finalexam.dto.EmployeeResponse;
import com.miu.finalexam.model.RetirementPlan;
import com.miu.finalexam.repository.EmployeeRepository;
import com.miu.finalexam.repository.RetirementRpository;
import com.miu.finalexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private  RetirementRpository retirementRpository;



    @Override
    public List<EmployeeResponse> getAllEmployeesWithRetirementPlans() {
        List<Employee> employees = employeeRepository.findAllByOrderByLastNameAsc();
        return employees.stream()
                .map(employee -> {
                    RetirementPlan plan = employee.getRetirementPlan();
                    return new EmployeeResponse(
                            employee.getEmployeeId(),
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getYearlySalary(),
                            new RetirementPlanResponse(plan.getPlanId(), plan.getReferenceNumber(),
                                    plan.getEnrollmentDate(),
                                    plan.getRetirementDate(),
                                    plan.getMonthlyContribution())

                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getRetirementPlanByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        RetirementPlan plan = employee.getRetirementPlan();
        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getYearlySalary(),
                new RetirementPlanResponse(plan.getPlanId(), plan.getReferenceNumber(),
                        plan.getEnrollmentDate(),
                        plan.getRetirementDate(),
                        plan.getMonthlyContribution())
        );
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        employee.setFirstName(employeeUpdateRequest.firstName());
        employee.setLastName(employeeUpdateRequest.lastName());
        employee.setYearlySalary(employeeUpdateRequest.yearlySalary());
        Employee updatedEmployee = employeeRepository.save(employee);

        RetirementPlan retirementPlan = updatedEmployee.getRetirementPlan();
        if (retirementPlan != null) {
            retirementPlan.setEnrollmentDate(employeeUpdateRequest.enrollmentDate());
            retirementPlan.setRetirementDate(employeeUpdateRequest.retirementDate());
            retirementPlan.setMonthlyContribution(employeeUpdateRequest.monthlyContribution());
            retirementRpository.save(retirementPlan);
        }
        return mapEmployeeToEmployeeResponse(employeeId,retirementPlan.getPlanId(),employeeUpdateRequest);
    }

    private EmployeeResponse mapEmployeeToEmployeeResponse(Long employeeId,Long planId,EmployeeUpdateRequest employee) {
       return new EmployeeResponse(employeeId,employee.firstName(),employee.lastName(),employee.yearlySalary(),
               new RetirementPlanResponse(planId,employee.referenceNumber(),employee.enrollmentDate(),employee.retirementDate(),employee.monthlyContribution()));
    }
}
