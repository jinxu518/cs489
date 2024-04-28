package com.miu.finalexam.service.impl;

import com.miu.finalexam.dto.NewRetirementPlanRequest;
import com.miu.finalexam.dto.RetirementPlanResponse;
import com.miu.finalexam.model.Employee;
import com.miu.finalexam.model.RetirementPlan;
import com.miu.finalexam.dto.EmployeeResponse;
import com.miu.finalexam.repository.EmployeeRepository;
import com.miu.finalexam.repository.RetirementRpository;
import com.miu.finalexam.service.RetirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetirementServiceImpl implements RetirementService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RetirementRpository retirementRpository;

    @Override
    public List<EmployeeResponse> getUpcomingRetirees(LocalDate startDate, LocalDate endDate) {
        List<RetirementPlan> upcomingRetirementPlans = retirementRpository.findByRetirementDateBetweenOrderByRetirementDateAsc(startDate, endDate);
        return upcomingRetirementPlans.stream()
                .map(plan -> {
                    return new EmployeeResponse(
                            plan.getEmployee().getEmployeeId(),
                            plan.getEmployee().getFirstName(),
                            plan.getEmployee().getLastName(),
                            plan.getEmployee().getYearlySalary(),
                            new RetirementPlanResponse(plan.getPlanId(), plan.getReferenceNumber(),
                                    plan.getEnrollmentDate(),
                                    plan.getRetirementDate(),
                                    plan.getMonthlyContribution())
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addNewRetirementPlan(NewRetirementPlanRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setYearlySalary(request.yearlySalary());
        employee = employeeRepository.save(employee);

        RetirementPlan retirementPlan = new RetirementPlan();
        retirementPlan.setReferenceNumber(request.referenceNumber());
        retirementPlan.setEnrollmentDate(request.enrollmentDate());
        retirementPlan.setRetirementDate(request.retirementDate());
        retirementPlan.setMonthlyContribution(request.monthlyContribution());
        retirementPlan.setEmployee(employee);
        retirementRpository.save(retirementPlan);
    }
}
