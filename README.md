Description
L'application Système de Gestion des Dépenses et des Budgets est une solution web permettant aux utilisateurs de gérer efficacement leurs finances en suivant leurs dépenses par rapport à des budgets définis. Elle vise à offrir une vue d’ensemble des budgets et des dépenses associés, permettant de suivre le montant restant disponible pour chaque budget et d’optimiser les ressources financières.

Fonctionnalités
Gestion des Budgets
Création d'un budget : Spécifiez le montant total, la date de début et la date de fin d'un budget, ainsi que l'utilisateur responsable.
Affichage des budgets : Visualisez tous les budgets avec leurs détails, y compris le montant restant en fonction des dépenses associées.
Modification et Suppression : Mettez à jour les informations d’un budget existant ou supprimez-le selon les besoins.
Gestion des Dépenses
Ajout de dépenses : Enregistrez des dépenses pour un budget spécifique en fournissant une description et un montant.
Affichage des dépenses : Visualisez toutes les dépenses associées à un budget donné et calculez automatiquement le montant restant.
Suivi du solde : L'application calcule le solde restant pour chaque budget en tenant compte des dépenses déjà enregistrées.
Interface Utilisateur
Page Liste des Budgets : Affiche les budgets créés par l’utilisateur avec leurs montants et dates.
Page Détail des Dépenses : Affiche les dépenses associées à chaque budget et le montant restant disponible.
Formulaires : Formulaires de création et de modification de budgets et de dépenses, avec une mise en page intuitive et simple.
Architecture
L'application est organisée en une architecture MVC (Modèle-Vue-Contrôleur) pour séparer les préoccupations :

Modèle : Définition des entités Budget et Dépense avec leurs relations.
Vue : Templates Thymeleaf pour les pages web, permettant une interaction facile avec les données.
Contrôleur : Gestion des requêtes HTTP et des opérations CRUD sur les budgets et dépenses.
Technologies Utilisées
Backend : Spring Boot - pour la gestion des API et des opérations métier.
Frontend : Thymeleaf - pour le rendu dynamique des pages HTML.
Base de Données : H2 (base en mémoire pour le développement), possibilité de passer en MySQL ou PostgreSQL.
IDE : IntelliJ IDEA - recommandé pour un développement fluide avec Spring Boot.

https://github.com/user-attachments/assets/ccebc259-55dd-4d61-94c3-fe5c35e074e2

