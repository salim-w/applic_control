package org.example.control_application.entities;
    import jakarta.persistence.*;

    @Entity
    public class Depense {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        private Double montant;

        @ManyToOne
        private Budget budget;

        public Depense() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getMontant() {
            return montant;
        }

        public void setMontant(Double montant) {
            this.montant = montant;
        }

        public Budget getBudget() {
            return budget;
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

    }


