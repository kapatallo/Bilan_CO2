package fr.univlyon1.m1if10.bilanCo2.model;

import java.io.Serializable;

/**
 * The type Ident.
 */
public class Ident implements Serializable {

    private int annee;

    private int semaine;

    private int id;

    /**
     * Instantiates a new Ident.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public Ident(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Ident.
     */
    public Ident() {
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
}
