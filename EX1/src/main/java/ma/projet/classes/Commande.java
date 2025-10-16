package ma.projet.classes;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> lignesCommande = new ArrayList<>();

    public Commande() {}

    public Commande(LocalDate date) {
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<LigneCommandeProduit> getLignesCommande() { return lignesCommande; }
    public void setLignesCommande(List<LigneCommandeProduit> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    @Override
    public String toString() {
        return String.format("Commande{id=%d, date=%s}", id, date);
    }
}
