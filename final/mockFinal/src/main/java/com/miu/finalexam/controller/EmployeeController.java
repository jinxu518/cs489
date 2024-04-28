package com.miu.finalexam.controller;

import com.miu.finalexam.dto.EmployeeUpdateRequest;
import com.miu.finalexam.service.EmployeeService;
import com.miu.finalexam.service.RetirementService;
import com.miu.finalexam.dto.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RetirementService retirementService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployeeList() {
        List<EmployeeResponse> plans = this.employeeService.getAllEmployeesWithRetirementPlans();
        return ResponseEntity.ok(plans);
    }
    // @RequestParam
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getPlanByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.getRetirementPlanByEmployeeId(employeeId);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/upcomingRetirees")
    public ResponseEntity<List<EmployeeResponse>> getUpcomingRetirees() {
        LocalDate firstDayOfNextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.plusMonths(1).minusDays(1);
        List<EmployeeResponse> upcomingRetirees = retirementService.getUpcomingRetirees(firstDayOfNextMonth, lastDayOfNextMonth);
        return ResponseEntity.ok(upcomingRetirees);
    }

    /**
     *  just practices not in mock final exam
     * @param employeeId
     * @param employeeUpdateRequest
     * @return
     */
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody @Valid EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(employeeId, employeeUpdateRequest);
        return ResponseEntity.ok(updatedEmployee);
    }
}
