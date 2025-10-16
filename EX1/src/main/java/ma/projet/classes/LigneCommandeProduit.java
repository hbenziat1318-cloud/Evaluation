package ma.projet.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "lignes_commande")
public class LigneCommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public LigneCommandeProduit() {}

    public LigneCommandeProduit(int quantite, Commande commande, Produit produit) {
        this.quantite = quantite;
        this.commande = commande;
        this.produit = produit;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    @Override
    public String toString() {
        return String.format("LigneCommande{id=%d, quantite=%d, produit=%s}",
                id, quantite, produit != null ? produit.getReference() : "null");
    }
}
