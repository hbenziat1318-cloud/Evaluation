package ma.projet.classes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
@NamedQuery(
        name = "Produit.findByPrixGreaterThan",
        query = "SELECT p FROM Produit p WHERE p.prix > :prix"
)
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reference;
    private float prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> lignesCommande = new ArrayList<>();

    public Produit() {}

    public Produit(String reference, float prix, Categorie categorie) {
        this.reference = reference;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public List<LigneCommandeProduit> getLignesCommande() { return lignesCommande; }
    public void setLignesCommande(List<LigneCommandeProduit> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    @Override
    public String toString() {
        return String.format("Produit{id=%d, reference='%s', prix=%.2f}", id, reference, prix);
    }
}
