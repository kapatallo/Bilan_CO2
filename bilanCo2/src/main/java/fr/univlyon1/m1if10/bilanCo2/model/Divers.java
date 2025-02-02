package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity; // for Spring Boot 3
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;

/**
 * The type Divers.
 */
@Entity
@Table(name = "divers")
@IdClass(Ident.class)
public class Divers {

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
     * @param annee       the annee
     * @param semaine     the semaine
     * @param id          the id
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
     * Instantiates a new Divers.
     *
     * @param newDivers the new divers
     */
    public  Divers(final Divers newDivers) {
        this.annee = newDivers.getAnnee();
        this.semaine = newDivers.getSemaine();
        this.id = newDivers.getId();
        this.totalDivers = newDivers.getTotalDivers();
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
