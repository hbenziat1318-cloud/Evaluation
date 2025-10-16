package ma.projet.classes;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "taches")
@NamedQuery(
        name = "Tache.findByPrixGreaterThan",
        query = "SELECT t FROM Tache t WHERE t.prix > :prix"
)
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double prix;

    @Column(name = "date_debut_planifie")
    private LocalDate dateDebutPlanifie;

    @Column(name = "date_fin_planifie")
    private LocalDate dateFinPlanifie;

    @Column(name = "date_debut_reelle")
    private LocalDate dateDebutReelle;

    @Column(name = "date_fin_reelle")
    private LocalDate dateFinReelle;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache", cascade = CascadeType.ALL)
    private List<EmployeeTache> employeeTaches = new ArrayList<>();

    public Tache() {}

    public Tache(String nom, double prix, LocalDate dateDebutPlanifie, LocalDate dateFinPlanifie, Projet projet) {
        this.nom = nom;
        this.prix = prix;
        this.dateDebutPlanifie = dateDebutPlanifie;
        this.dateFinPlanifie = dateFinPlanifie;
        this.projet = projet;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public LocalDate getDateDebutPlanifie() { return dateDebutPlanifie; }
    public void setDateDebutPlanifie(LocalDate dateDebutPlanifie) { this.dateDebutPlanifie = dateDebutPlanifie; }

    public LocalDate getDateFinPlanifie() { return dateFinPlanifie; }
    public void setDateFinPlanifie(LocalDate dateFinPlanifie) { this.dateFinPlanifie = dateFinPlanifie; }

    public LocalDate getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(LocalDate dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }

    public LocalDate getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(LocalDate dateFinReelle) { this.dateFinReelle = dateFinReelle; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public List<EmployeeTache> getEmployeeTaches() { return employeeTaches; }
    public void setEmployeeTaches(List<EmployeeTache> employeeTaches) { this.employeeTaches = employeeTaches; }

    @Override
    public String toString() {
        return String.format("Tache{id=%d, nom='%s', prix=%.2f}", id, nom, prix);
    }
}