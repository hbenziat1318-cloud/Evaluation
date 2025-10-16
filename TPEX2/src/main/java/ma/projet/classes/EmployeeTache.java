package ma.projet.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_tache")
public class EmployeeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    private int heures;

    public EmployeeTache() {}

    public EmployeeTache(Employee employee, Tache tache, int heures) {
        this.employee = employee;
        this.tache = tache;
        this.heures = heures;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Tache getTache() { return tache; }
    public void setTache(Tache tache) { this.tache = tache; }

    public int getHeures() { return heures; }
    public void setHeures(int heures) { this.heures = heures; }

    @Override
    public String toString() {
        return String.format("EmployeeTache{id=%d, employee=%s, tache=%s, heures=%d}",
                id, employee != null ? employee.getNom() : "null",
                tache != null ? tache.getNom() : "null", heures);
    }
}