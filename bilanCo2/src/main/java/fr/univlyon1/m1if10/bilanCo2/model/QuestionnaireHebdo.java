package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;

/**
 * The type Questionnaire hebdo.
 */
@Entity
@Table(name = "questionnaire_hebdo")
public class QuestionnaireHebdo {

    @Id
    private int annee;

    @Id
    private int semaine;

    @Id
    @JoinColumn(name = "utilisateur_infos", nullable = false,
            foreignKey = @ForeignKey(name = "id"))
    private int id;

    @Column(name = "total_emission")
    private int totalEmission;

    /**
     * Instantiates a new Questionnaire hebdo.
     */
    public QuestionnaireHebdo() {

    }

    /**
     * Instantiates a new Questionnaire hebdo.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public QuestionnaireHebdo(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Questionnaire hebdo.
     *
     * @param annee         the annee
     * @param semaine       the semaine
     * @param id            the id
     * @param totalEmission the total emission
     */
    public QuestionnaireHebdo(final int annee, final int semaine, final int id,
                              final int totalEmission) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
        this.totalEmission = totalEmission;
    }

    /**
     * Instantiates a new Questionnaire hebdo.
     *
     * @param newQuestionnaireHebdo the new questionnaire hebdo
     */
    public  QuestionnaireHebdo(final QuestionnaireHebdo newQuestionnaireHebdo) {
        this.annee = newQuestionnaireHebdo.getAnnee();
        this.semaine = newQuestionnaireHebdo.getSemaine();
        this.id = newQuestionnaireHebdo.getId();
        this.totalEmission = newQuestionnaireHebdo.getTotalEmission();
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
     * Gets total emission.
     *
     * @return the total emission
     */
    public int getTotalEmission() {
        return totalEmission;
    }

    /**
     * Sets total emission.
     *
     * @param totalEmission the total emission
     */
    public void setTotalEmission(final int totalEmission) {
        this.totalEmission = totalEmission;
    }
}
