package com.miu.finalexam.repository;

import com.miu.finalexam.model.RetirementPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RetirementRpository extends JpaRepository<RetirementPlan, Long> {
    List<RetirementPlan> findByRetirementDateBetweenOrderByRetirementDateAsc(LocalDate start, LocalDate end);
}
