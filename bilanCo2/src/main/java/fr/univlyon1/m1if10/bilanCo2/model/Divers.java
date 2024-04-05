package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Divers.
 */
@Entity
@Table(name = "divers")
public class Divers {

    @Id
    private int annee;

    @Id
    private int semaine;

    @Id
    private int id;

    @Column(name = "total_divers")
    private int totalDivers;

    /**
     * Instantiates a new Divers.
     */
    public Divers() {

    }

    /**
     * Instantiates a new Divers.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public Divers(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Divers.
     *
     * @param annee             the annee
     * @param semaine           the semaine
     * @param id                the id
     * @param totalDivers the total divers
     */
    public Divers(final int annee, final int semaine, final int id,
                        final int totalDivers) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
        this.totalDivers = totalDivers;
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
     * Gets total divers.
     *
     * @return the total divers
     */
    public int getTotalDivers() {
        return totalDivers;
    }

    /**
     * Sets total divers.
     *
     * @param totalDivers the total divers
     */
    public void setTotalDivers(final int totalDivers) {
        this.totalDivers = totalDivers;
    }
}
