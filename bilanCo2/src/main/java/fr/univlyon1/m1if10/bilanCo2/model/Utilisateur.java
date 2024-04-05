package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity; // for Spring Boot 3
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;

/**
 * The type Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDate ddn;

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
     * Instantiates a new Utilisateur.
     *
     * @param nom    the nom
     * @param prenom the prenom
     * @param email  the email
     * @param mdp    the mdp
     * @param ddn    the ddn
     */
    public Utilisateur(final String nom, final String prenom, final String email, final String mdp,
                       final LocalDate ddn) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.ddn = ddn;
    }

    public  Utilisateur(final Utilisateur newUtilisateur) {
        this.nom = newUtilisateur.getNom();
        this.prenom = newUtilisateur.getPrenom();
        this.email = newUtilisateur.getEmail();
        this.mdp = newUtilisateur.getMdp();
        this.ddn = newUtilisateur.getDdn();
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets prenom.
     *
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets prenom.
     *
     * @param prenom the prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets mdp.
     *
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Sets mdp.
     *
     * @param mdp the mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Gets ddn.
     *
     * @return the ddn
     */
    public LocalDate getDdn() {
        return ddn;
    }

    /**
     * Sets ddn.
     *
     * @param ddn the ddn
     */
    public void setDdn(LocalDate ddn) {
        this.ddn = ddn;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", prenom: " + prenom + ", nom: " + nom + ", email: " + email + ", ddn: " + ddn + "]";
    }
}
