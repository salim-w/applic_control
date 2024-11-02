package org.example.control_application.repository;

    import org.example.control_application.entities.Budget;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUtilisateur(String utilisateur);
    }
