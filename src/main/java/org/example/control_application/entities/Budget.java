package org.example.control_application.entities;
    import jakarta.persistence.Id;
    import jakarta.persistence.*;
    import java.time.LocalDate;
    import java.util.List;
    @Entity
    public class Budget {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Double montantTotal;
        private LocalDate dateDebut;
        private LocalDate dateFin;
        private String utilisateur;


        @OneToMany(mappedBy = "budget", cascade = CascadeType.REMOVE)
        private List<Depense> depenses;

        public Budget() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Double getMontantTotal() {
            return montantTotal;
        }

        public void setMontantTotal(Double montantTotal) {
            this.montantTotal = montantTotal;
        }

        public LocalDate getDateDebut() {
            return dateDebut;
        }

        public void setDateDebut(LocalDate dateDebut) {
            this.dateDebut = dateDebut;
        }

        public LocalDate getDateFin() {
            return dateFin;
        }

        public void setDateFin(LocalDate dateFin) {
            this.dateFin = dateFin;
        }

        public List<Depense> getDepenses() {
            return depenses;
        }

        public void setDepenses(List<Depense> depenses) {
            this.depenses = depenses;
        }

        public String getUtilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(String utilisateur) {
            this.utilisateur = utilisateur;
        }
    }
