package ma.projet.classes;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hommes")
public class Homme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariages = new ArrayList<>();

    public Homme() {}

    public Homme(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }

    @Override
    public String toString() {
        return String.format("Homme{id=%d, nom='%s', prenom='%s'}", id, nom, prenom);
    }
}