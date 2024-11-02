
package org.example.control_application.controller;

import org.example.control_application.entities.Depense;
import org.example.control_application.repository.BudgetRepository;
import org.example.control_application.repository.DepenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class DepenseController {

    @Autowired
    private DepenseRepository depenseRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @GetMapping("/depenses/budget/{budgetId}")
    public String listDepenses(@PathVariable("budgetId") long budgetId, Model model) {
        // Récupérer les dépenses associées au budget
        model.addAttribute("depenses", depenseRepository.findByBudgetId(budgetId));

        // Récupérer le budget
        var budget = budgetRepository.findById(budgetId).orElse(null);
        model.addAttribute("budget", budget);

        if (budget != null) {
            // Calculer le montant total des dépenses
            double totalDepenses = depenseRepository.findByBudgetId(budgetId).stream()
                    .mapToDouble(Depense::getMontant)
                    .sum();

            // Calculer le montant restant
            double montantRestant = budget.getMontantTotal() - totalDepenses;
            model.addAttribute("montantRestant", montantRestant);
        } else {
            model.addAttribute("montantRestant", 0.0); // Si le budget n'existe pas
        }

        return "depense/list";
    }

    @GetMapping("/depenses/add/{budgetId}")
    public String showAddDepenseForm(@PathVariable("budgetId") long budgetId, Depense depense, Model model) {
        model.addAttribute("budgetId", budgetId);
        return "depense/add-depense";
    }

    @PostMapping("/depenses/add/{budgetId}")
    public String addDepense(@PathVariable("budgetId") long budgetId, @Valid Depense depense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("budgetId", budgetId);
            return "depense/add-depense";
        }

        // Vérification de l'existence du budget avant d'assigner à la dépense
        depense.setBudget(budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid budget Id:" + budgetId)));
        depenseRepository.save(depense);
        return "redirect:/depenses/budget/" + budgetId;
    }

    @GetMapping("/depenses/edit/{id}")
    public String showUpdateDepenseForm(@PathVariable("id") long id, Model model) {
        Depense depense = depenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid depense Id:" + id));
        model.addAttribute("depense", depense);
        return "depense/update-depense";
    }

    @PostMapping("/depenses/update/{id}")
    public String updateDepense(@PathVariable("id") long id, @Valid Depense depense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            depense.setId(id);
            return "depense/update-depense";
        }

        // Charger l'instance actuelle de Depense de la base de données
        Depense existingDepense = depenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid depense Id:" + id));

        // Conserver le budget actuel
        depense.setBudget(existingDepense.getBudget());

        // Enregistrer les changements
        depenseRepository.save(depense);
        return "redirect:/depenses/budget/" + depense.getBudget().getId();
    }

    @GetMapping("/depenses/delete/{id}")
    public String deleteDepense(@PathVariable("id") long id, Model model) {
        Depense depense = depenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid depense Id:" + id));

        Long budgetId = (depense.getBudget() != null) ? depense.getBudget().getId() : null;

        if (budgetId != null) {
            depenseRepository.delete(depense);
            return "redirect:/depenses/budget/" + budgetId;
        } else {
            throw new IllegalArgumentException("Cannot delete depense without a valid budget.");
        }
    }
}
