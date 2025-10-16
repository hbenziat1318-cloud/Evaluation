package ma.projet.classes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeTache> employeeTaches = new ArrayList<>();

    public Employee() {}

    public Employee(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<EmployeeTache> getEmployeeTaches() { return employeeTaches; }
    public void setEmployeeTaches(List<EmployeeTache> employeeTaches) { this.employeeTaches = employeeTaches; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, nom='%s', prenom='%s'}", id, nom, prenom);
    }
}
