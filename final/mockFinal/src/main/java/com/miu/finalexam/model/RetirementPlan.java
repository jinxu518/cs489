package com.miu.finalexam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "retirement_plan")
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long planId;
    @Column(nullable = false, unique = true)
    private String referenceNumber;
    @Column(nullable = false)
    private LocalDate enrollmentDate;
    @Column(nullable = false)
    private LocalDate retirementDate;
    @Column(nullable = true)
    private BigDecimal monthlyContribution;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
