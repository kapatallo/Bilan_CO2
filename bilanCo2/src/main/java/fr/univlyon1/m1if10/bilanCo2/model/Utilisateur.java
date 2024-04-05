package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "ddn")
    private String ddn;

    public Utilisateur() {

    }

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nom=" + nom + ", email=" + email + "]";
    }
}
