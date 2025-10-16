package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import java.time.LocalDate;

public class TestExercice2 {
    public static void main(String[] args) {
        try {
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeeService employeeService = new EmployeeService();
            EmployeeTacheService employeeTacheService = new EmployeeTacheService();

            System.out.println("=== DÉBUT DES TESTS EXERCICE 2 ===\n");

            // 1. Création des projets
            System.out.println("1. Création des projets...");
            Projet projet1 = new Projet("Gestion de stock", LocalDate.of(2013, 1, 14));
            Projet projet2 = new Projet("Site Web E-commerce", LocalDate.of(2013, 2, 1));
            projetService.create(projet1);
            projetService.create(projet2);

            // 2. Création des tâches
            System.out.println("2. Création des tâches...");
            Tache tache1 = new Tache("Analyse", 1500.0,
                    LocalDate.of(2013, 2, 10), LocalDate.of(2013, 2, 20), projet1);
            Tache tache2 = new Tache("Conception", 2000.0,
                    LocalDate.of(2013, 3, 10), LocalDate.of(2013, 3, 15), projet1);
            Tache tache3 = new Tache("Développement", 5000.0,
                    LocalDate.of(2013, 4, 10), LocalDate.of(2013, 4, 25), projet1);
            Tache tache4 = new Tache("Design", 800.0,
                    LocalDate.of(2013, 2, 15), LocalDate.of(2013, 2, 28), projet2);

            tache1.setDateDebutReelle(LocalDate.of(2013, 2, 10));
            tache1.setDateFinReelle(LocalDate.of(2013, 2, 20));
            tache2.setDateDebutReelle(LocalDate.of(2013, 3, 10));
            tache2.setDateFinReelle(LocalDate.of(2013, 3, 15));
            tache3.setDateDebutReelle(LocalDate.of(2013, 4, 10));
            tache3.setDateFinReelle(LocalDate.of(2013, 4, 25));

            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            tacheService.create(tache4);

            // 3. Création des employés
            System.out.println("3. Création des employés...");
            Employee emp1 = new Employee("SAFI", "Ahmed", "ahmed.safi@email.com");
            Employee emp2 = new Employee("ALAMI", "Fatima", "fatima.alami@email.com");
            Employee emp3 = new Employee("RAHMOUNI", "Karim", "karim.rahmouni@email.com");
            employeeService.create(emp1);
            employeeService.create(emp2);
            employeeService.create(emp3);

            // 4. Création des affectations
            System.out.println("4. Création des affectations employé-tâche...");
            EmployeeTache et1 = new EmployeeTache(emp1, tache1, 40);
            EmployeeTache et2 = new EmployeeTache(emp2, tache1, 30);
            EmployeeTache et3 = new EmployeeTache(emp1, tache2, 50);
            EmployeeTache et4 = new EmployeeTache(emp3, tache3, 80);
            EmployeeTache et5 = new EmployeeTache(emp2, tache4, 25);
            employeeTacheService.create(et1);
            employeeTacheService.create(et2);
            employeeTacheService.create(et3);
            employeeTacheService.create(et4);
            employeeTacheService.create(et5);

            // TESTS
            System.out.println("\n=== TEST 1: Tâches réalisées par un employé ===");
            System.out.println("Tâches réalisées par " + emp1.getNom() + " " + emp1.getPrenom() + ":");
            employeeService.getTachesRealiseesByEmployee(emp1.getId()).forEach(System.out::println);

            System.out.println("\n=== TEST 2: Projets gérés par un employé ===");
            System.out.println("Projets gérés par " + emp2.getNom() + " " + emp2.getPrenom() + ":");
            employeeService.getProjetsByEmployee(emp2.getId()).forEach(System.out::println);

            System.out.println("\n=== TEST 3: Tâches planifiées pour un projet ===");
            System.out.println("Tâches planifiées pour le projet '" + projet1.getNom() + "':");
            projetService.getTachesPlanifieesByProjet(projet1.getId()).forEach(System.out::println);

            System.out.println("\n=== TEST 4: Tâches réalisées avec détails (comme demandé) ===");
            projetService.afficherTachesRealiseesAvecDetails(projet1.getId());

            System.out.println("\n=== TEST 5: Tâches avec prix > 1000 DH (requête nommée) ===");
            tacheService.getTachesPrixSuperieurA1000().forEach(t ->
                    System.out.println("  - " + t.getNom() + " (" + t.getPrix() + " DH)")
            );

            System.out.println("\n=== TEST 6: Tâches réalisées entre deux dates ===");
            LocalDate dateDebut = LocalDate.of(2013, 3, 1);
            LocalDate dateFin = LocalDate.of(2013, 4, 30);
            System.out.println("Tâches réalisées entre " + dateDebut + " et " + dateFin + ":");
            tacheService.getTachesRealiseesBetweenDates(dateDebut, dateFin).forEach(System.out::println);

            System.out.println("\nTous les tests de l'exercice 2 ont été exécutés avec succès !");

        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
