package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity; // for Spring Boot 3
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/**
 * The type Utilisateur.
 */
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

    /**
     * Instantiates a new Utilisateur.
     */
    public Utilisateur() {

    }

    /**
     * Instantiates a new Utilisateur.
     *
     * @param email the email
     * @param mdp   the mdp
     */
    public Utilisateur(final String email, final String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return nom;
    }

    /**
     * Sets name.
     *
     * @param nom the nom
     */
    public void setName(final String nom) {
        this.nom = nom;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return email;
    }

    /**
     * Sets mail.
     *
     * @param email the email
     */
    public void setMail(final String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nom=" + nom + ", email=" + email + "]";
    }
}
