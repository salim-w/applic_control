package org.example.control_application.repository;
import org.example.control_application.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface DepenseRepository extends JpaRepository<Depense, Long> {
        List<Depense> findByBudgetId(Long budgetId);
    }


