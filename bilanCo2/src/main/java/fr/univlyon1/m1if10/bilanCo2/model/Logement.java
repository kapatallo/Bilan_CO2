package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Logement.
 */
@Entity
@Table(name = "logement")
public class Logement {

    @Id
    private int annee;

    @Id
    private int semaine;

    @Id
    private int id;

    @Column(name = "total_logement")
    private int totalLogement;

    /**
     * Instantiates a new Logement.
     */
    public Logement() {

    }

    /**
     * Instantiates a new Logement.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public Logement(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Logement.
     *
     * @param annee             the annee
     * @param semaine           the semaine
     * @param id                the id
     * @param totalLogement the total logement
     */
    public Logement(final int annee, final int semaine, final int id,
                        final int totalLogement) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
        this.totalLogement = totalLogement;
    }

    /**
     * Gets annee.
     *
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Sets annee.
     *
     * @param annee the annee
     */
    public void setAnnee(final int annee) {
        this.annee = annee;
    }

    /**
     * Gets semaine.
     *
     * @return the semaine
     */
    public int getSemaine() {
        return semaine;
    }

    /**
     * Sets semaine.
     *
     * @param semaine the semaine
     */
    public void setSemaine(final int semaine) {
        this.semaine = semaine;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets total logement.
     *
     * @return the total logement
     */
    public int getTotalLogement() {
        return totalLogement;
    }

    /**
     * Sets total logement.
     *
     * @param totalLogement the total logement
     */
    public void setTotalLogement(final int totalLogement) {
        this.totalLogement = totalLogement;
    }
}
