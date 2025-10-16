package ma.projet.classes;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mariages")
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    private int nbrEnfants;

    @ManyToOne
    @JoinColumn(name = "homme_id")
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme_id")
    private Femme femme;

    public Mariage() {}

    public Mariage(LocalDate dateDebut, LocalDate dateFin, int nbrEnfants, Homme homme, Femme femme) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
        this.homme = homme;
        this.femme = femme;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public int getNbrEnfants() { return nbrEnfants; }
    public void setNbrEnfants(int nbrEnfants) { this.nbrEnfants = nbrEnfants; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }

    @Override
    public String toString() {
        return String.format("Mariage{id=%d, dateDebut=%s, dateFin=%s, nbrEnfants=%d}",
                id, dateDebut, dateFin, nbrEnfants);
    }
}