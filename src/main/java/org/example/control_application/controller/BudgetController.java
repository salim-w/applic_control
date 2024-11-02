package org.example.control_application.controller;

import org.example.control_application.entities.Budget;
import org.example.control_application.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    // Afficher la liste des budgets
    @GetMapping("/budgets")
    public String listBudgets(Model model) {
        model.addAttribute("budgets", budgetRepository.findAll());
        return "budget/list";
    }

    // Afficher le formulaire pour ajouter un nouveau budget
    @GetMapping("/budgets/add")
    public String showAddBudgetForm(Budget budget) {
        return "budget/add-budget";
    }

    // Ajouter un nouveau budget
    @PostMapping("/budgets/add")
    public String addBudget(@Valid Budget budget, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "budget/add-budget";
        }
        budgetRepository.save(budget);  // Sauvegarde du nouveau budget
        return "redirect:/budgets";
    }

    // Afficher le formulaire pour modifier un budget existant
    @GetMapping("/budgets/edit/{id}")
    public String showUpdateBudgetForm(@PathVariable("id") long id, Model model) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget Id:" + id));
        model.addAttribute("budget", budget);
        return "budget/update-budget";
    }

    // Mettre à jour un budget existant
    @PostMapping("/budgets/update/{id}")
    public String updateBudget(@PathVariable("id") long id, @Valid Budget budget, BindingResult result, Model model) {
        if (result.hasErrors()) {
            budget.setId(id);
            return "budget/update-budget";
        }
        budgetRepository.save(budget);  // Sauvegarde des modifications du budget
        return "redirect:/budgets";
    }

    // Supprimer un budget
    @GetMapping("/budgets/delete/{id}")
    public String deleteBudget(@PathVariable("id") long id, Model model) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget Id:" + id));
        budgetRepository.delete(budget);  // Suppression du budget
        return "redirect:/budgets";
    }
}
