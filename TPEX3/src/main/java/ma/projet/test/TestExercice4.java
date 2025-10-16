package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import java.time.LocalDate;

public class TestExercice4 {
    public static void main(String[] args) {
        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            System.out.println("=== DÉBUT DES TESTS EXERCICE 3 ===\n");

            System.out.println("1. Création des femmes et hommes...");

            Homme homme1 = new Homme("SAFT", "SAID", LocalDate.of(1960, 1, 15));
            Homme homme2 = new Homme("ALAMI", "MOHAMED", LocalDate.of(1970, 5, 20));
            Homme homme3 = new Homme("RAHMOUNI", "HASSAN", LocalDate.of(1965, 3, 10));
            Homme homme4 = new Homme("BENNANI", "KARIM", LocalDate.of(1975, 8, 5));
            Homme homme5 = new Homme("CHAKIR", "ABDELLAH", LocalDate.of(1980, 12, 25));

            hommeService.create(homme1);
            hommeService.create(homme2);
            hommeService.create(homme3);
            hommeService.create(homme4);
            hommeService.create(homme5);

            Femme femme1 = new Femme("SALIMA", "RAMI", LocalDate.of(1970, 2, 10));
            Femme femme2 = new Femme("AMAL", "ALI", LocalDate.of(1975, 6, 15));
            Femme femme3 = new Femme("WAFA", "ALAOUI", LocalDate.of(1980, 9, 20));
            Femme femme4 = new Femme("KARIMA", "ALAMI", LocalDate.of(1965, 4, 5));
            Femme femme5 = new Femme("FATIMA", "BERADA", LocalDate.of(1972, 11, 30));
            Femme femme6 = new Femme("NADIA", "CHAKIR", LocalDate.of(1978, 7, 12));
            Femme femme7 = new Femme("SOUAD", "RAHMOUNI", LocalDate.of(1968, 3, 8));
            Femme femme8 = new Femme("HIND", "BENNANI", LocalDate.of(1976, 10, 17));
            Femme femme9 = new Femme("LEILA", "SAFT", LocalDate.of(1973, 12, 3));
            Femme femme10 = new Femme("ZINEB", "MOUNA", LocalDate.of(1969, 1, 22));

            femmeService.create(femme1);
            femmeService.create(femme2);
            femmeService.create(femme3);
            femmeService.create(femme4);
            femmeService.create(femme5);
            femmeService.create(femme6);
            femmeService.create(femme7);
            femmeService.create(femme8);
            femmeService.create(femme9);
            femmeService.create(femme10);

            System.out.println("2. Création des mariages...");

            Mariage m1 = new Mariage(LocalDate.of(1990, 9, 3), null, 4, homme1, femme1);
            Mariage m2 = new Mariage(LocalDate.of(1995, 9, 3), null, 2, homme1, femme2);
            Mariage m3 = new Mariage(LocalDate.of(2000, 11, 4), null, 3, homme1, femme3);
            Mariage m4 = new Mariage(LocalDate.of(1989, 9, 3), LocalDate.of(1990, 9, 3), 0, homme1, femme4);

            Mariage m5 = new Mariage(LocalDate.of(1995, 1, 1), null, 2, homme2, femme5);
            Mariage m6 = new Mariage(LocalDate.of(2000, 1, 1), null, 1, homme2, femme6);
            Mariage m7 = new Mariage(LocalDate.of(2005, 1, 1), LocalDate.of(2010, 1, 1), 0, homme2, femme7);

            Mariage m8 = new Mariage(LocalDate.of(1998, 6, 1), null, 3, homme3, femme8);
            Mariage m9 = new Mariage(LocalDate.of(2002, 6, 1), null, 2, homme3, femme9);
            Mariage m10 = new Mariage(LocalDate.of(2006, 6, 1), null, 1, homme3, femme10);
            Mariage m11 = new Mariage(LocalDate.of(2010, 6, 1), null, 0, homme3, femme1);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);
            mariageService.create(m11);

            System.out.println("\n=== TEST 1: Liste des femmes ===");
            femmeService.getAll().forEach(System.out::println);

            System.out.println("\n=== TEST 2: Femme la plus âgée ===");
            Femme femmePlusAgee = femmeService.getFemmeLaPlusAgee();
            if (femmePlusAgee != null) {
                System.out.println("Femme la plus âgée : " + femmePlusAgee.getNom() + " " +
                        femmePlusAgee.getPrenom() + " (née le " + femmePlusAgee.getDateNaissance() + ")");
            }

            System.out.println("\n=== TEST 3: Épouses d'un homme entre deux dates ===");
            hommeService.afficherEpousesEntreDates(homme1.getId(),
                    LocalDate.of(1990, 1, 1), LocalDate.of(2000, 12, 31));

            System.out.println("\n=== TEST 4: Nombre d'enfants d'une femme entre deux dates ===");
            int nbrEnfants = femmeService.getNombreEnfantsEntreDates(femme1.getId(),
                    LocalDate.of(1990, 1, 1), LocalDate.of(2000, 12, 31));
            System.out.println("Nombre d'enfants de " + femme1.getNom() + " " + femme1.getPrenom() +
                    " entre 1990 et 2000 : " + nbrEnfants);

            System.out.println("\n=== TEST 5: Femmes mariées deux fois ou plus ===");
            femmeService.getFemmesMarieesDeuxFoisOuPlus().forEach(f ->
                    System.out.println("  - " + f.getNom() + " " + f.getPrenom())
            );

            System.out.println("\n=== TEST 6: Hommes mariés à quatre femmes entre deux dates (CRITERIA) ===");
            long nbrHommes = hommeService.getNombreHommesMariesQuatreFemmesEntreDates(
                    LocalDate.of(1990, 1, 1), LocalDate.of(2010, 12, 31));
            System.out.println("Nombre d'hommes mariés à 4 femmes ou plus entre 1990 et 2010 : " + nbrHommes);

            System.out.println("\n=== TEST 7: Mariages d'un homme avec détails (comme demandé) ===");
            hommeService.afficherMariagesAvecDetails(homme1.getId());

            System.out.println("\nTous les tests de l'exercice 3 ont été exécutés avec succès !");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}