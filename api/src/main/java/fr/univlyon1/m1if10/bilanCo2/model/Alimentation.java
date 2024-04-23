package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;

/**
 * The type Alimentation.
 */
@Entity
@Table(name = "alimentation")
public class Alimentation {

    @Id
    @JoinColumn(name = "questionnaire_hebdo", nullable = false,
            foreignKey = @ForeignKey(name = "annee"))
    private int annee;

    @Id
    @JoinColumn(name = "questionnaire_hebdo", nullable = false,
            foreignKey = @ForeignKey(name = "semaine"))
    private int semaine;

    @Id
    @JoinColumn(name = "questionnaire_hebdo", nullable = false,
            foreignKey = @ForeignKey(name = "id"))
    private int id;

    @Column(name = "total_alimentation")
    private int totalAlimentation;

    /**
     * Instantiates a new Alimentation.
     */
    public Alimentation() {

    }

    /**
     * Instantiates a new Alimentation.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public Alimentation(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Alimentation.
     *
     * @param annee             the annee
     * @param semaine           the semaine
     * @param id                the id
     * @param totalAlimentation the total alimentation
     */
    public Alimentation(final int annee, final int semaine, final int id,
                        final int totalAlimentation) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
        this.totalAlimentation = totalAlimentation;
    }

    /**
     * Instantiates a new Alimentation.
     *
     * @param newAlimentation the new alimentation
     */
    public  Alimentation(final Alimentation newAlimentation) {
        this.annee = newAlimentation.getAnnee();
        this.semaine = newAlimentation.getSemaine();
        this.id = newAlimentation.getId();
        this.totalAlimentation = newAlimentation.getTotalAlimentation();
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
     * Gets total alimentation.
     *
     * @return the total alimentation
     */
    public int getTotalAlimentation() {
        return totalAlimentation;
    }

    /**
     * Sets total alimentation.
     *
     * @param totalAlimentation the total alimentation
     */
    public void setTotalAlimentation(final int totalAlimentation) {
        this.totalAlimentation = totalAlimentation;
    }
}
