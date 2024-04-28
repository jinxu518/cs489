package com.miu.finalexam.controller;

import com.miu.finalexam.dto.NewRetirementPlanRequest;
import com.miu.finalexam.service.RetirementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retirement-plan")
public class RetirementPlanController {
    @Autowired
    private RetirementService retirementPlanService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewRetirementPlan(@RequestBody @Valid NewRetirementPlanRequest request) {
        retirementPlanService.addNewRetirementPlan(request);
        return new ResponseEntity<>("New retirement plan added successfully", HttpStatus.CREATED);
    }
}
