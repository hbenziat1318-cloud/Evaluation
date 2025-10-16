package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import java.time.LocalDate;

public class TestExercice1 {
    public static void main(String[] args) {
        try {
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            System.out.println("=== DÉBUT DES TESTS EXERCICE 1 ===\n");

            // Création des catégories
            System.out.println("1. Création des catégories...");
            Categorie categorieInfo = new Categorie("INFO", "Produits Informatiques");
            Categorie categorieBureau = new Categorie("BUREAU", "Matériel de Bureau");
            categorieService.create(categorieInfo);
            categorieService.create(categorieBureau);

            // Création des produits
            System.out.println("2. Création des produits...");
            Produit produit1 = new Produit("ES12", 120.0f, categorieInfo);
            Produit produit2 = new Produit("ZR85", 100.0f, categorieInfo);
            Produit produit3 = new Produit("EF85", 200.0f, categorieBureau);
            Produit produit4 = new Produit("AB50", 80.0f, categorieBureau);
            produitService.create(produit1);
            produitService.create(produit2);
            produitService.create(produit3);
            produitService.create(produit4);

            // Création des commandes
            System.out.println("3. Création des commandes...");
            Commande commande1 = new Commande(LocalDate.of(2013, 3, 14));
            Commande commande2 = new Commande(LocalDate.of(2013, 3, 20));
            commandeService.create(commande1);
            commandeService.create(commande2);

            // Création des lignes de commande
            System.out.println("4. Création des lignes de commande...");
            LigneCommandeProduit ligne1 = new LigneCommandeProduit(7, commande1, produit1);
            LigneCommandeProduit ligne2 = new LigneCommandeProduit(14, commande1, produit2);
            LigneCommandeProduit ligne3 = new LigneCommandeProduit(5, commande1, produit3);
            LigneCommandeProduit ligne4 = new LigneCommandeProduit(3, commande2, produit4);
            ligneCommandeService.create(ligne1);
            ligneCommandeService.create(ligne2);
            ligneCommandeService.create(ligne3);
            ligneCommandeService.create(ligne4);

            // TESTS
            System.out.println("\n=== TEST 1: Produits par catégorie ===");
            produitService.getProduitsByCategorie(categorieInfo).forEach(System.out::println);

            System.out.println("\n=== TEST 2: Produits commandés entre dates ===");
            produitService.getProduitsCommandesBetweenDates(
                    LocalDate.of(2013, 3, 1),
                    LocalDate.of(2013, 3, 31)
            ).forEach(System.out::println);

            System.out.println("\n=== TEST 3: Produits d'une commande ===");
            commandeService.afficherProduitsCommande(commande1.getId());

            System.out.println("\n=== TEST 4: Produits prix > 100 DH ===");
            produitService.getProduitsPrixSuperieurA100().forEach(System.out::println);

            System.out.println("\nTous les tests exécutés avec succès !");

        } catch (Exception e) {
            System.err.println(" Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}