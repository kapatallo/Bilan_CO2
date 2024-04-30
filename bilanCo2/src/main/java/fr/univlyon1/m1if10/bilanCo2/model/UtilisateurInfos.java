package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

/**
 * The type Utilisateur infos.
 */
@Entity
@Table(name = "utilisateur_infos")
public class UtilisateurInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idui;


    @JoinColumn(name = "utilisateur", nullable = false,
            foreignKey = @ForeignKey(name = "id"))
    private long id;

    @Column(name = "co2_g_km")
    private float cnit;

    @Column(name = "nb_pers_foyer")
    private int nbPersFoyer;

    @Column(name = "surface_logement")
    private int surfaceLogement;

    @Column(name = "hab_agglo")
    private int habAgglo;

    @Column(name = "possede_appartement")
    private String possedeAppartement;

    /**
     * Instantiates a new Utilisateur infos.
     */
    public UtilisateurInfos() {

    }

    /**
     * Instantiates a new Utilisateur infos.
     *
     * @param idui            the idui
     * @param id              the id
     * @param cnit            the cnit
     * @param nbPersFoyer     the nb pers foyer
     * @param surfaceLogement the surface logement
     * @param habAgglo        the hab agglo
     */
    public UtilisateurInfos(final long idui, final long id, final float cnit,
                            final int nbPersFoyer, final int surfaceLogement,
                            final int habAgglo, final String possedeA) {
        this.idui = idui;
        this.id = id;
        this.cnit = cnit;
        this.nbPersFoyer = nbPersFoyer;
        this.surfaceLogement = surfaceLogement;
        this.habAgglo = habAgglo;
        this.possedeAppartement = possedeA;
    }

    /**
     * Instantiates a new Utilisateur infos.
     *
     * @param newUtilisateurInfos the new utilisateur infos
     */
    public  UtilisateurInfos(final UtilisateurInfos newUtilisateurInfos) {
        this.idui = newUtilisateurInfos.getIdui();
        this.id = newUtilisateurInfos.getId();
        this.cnit = newUtilisateurInfos.getCnit();
        this.nbPersFoyer = newUtilisateurInfos.getNbPersFoyer();
        this.surfaceLogement = newUtilisateurInfos.getSurfaceLogement();
        this.habAgglo = newUtilisateurInfos.getHabAgglo();
        this.possedeAppartement = newUtilisateurInfos.getPossedeAppartement();
    }

    /**
     * Gets idui.
     *
     * @return the idui
     */
    public long getIdui() {
        return idui;
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
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets cnit.
     *
     * @return the cnit
     */
    public float getCnit() {
        return cnit;
    }

    /**
     * Sets cnit.
     *
     * @param cnit the cnit
     */
    public void setCnit(final float cnit) {
        this.cnit = cnit;
    }

    /**
     * Gets nb pers foyer.
     *
     * @return the nb pers foyer
     */
    public int getNbPersFoyer() {
        return nbPersFoyer;
    }

    /**
     * Sets nb pers foyer.
     *
     * @param nbPersFoyer the nb pers foyer
     */
    public void setNbPersFoyer(final int nbPersFoyer) {
        this.nbPersFoyer = nbPersFoyer;
    }

    /**
     * Gets surface logement.
     *
     * @return the surface logement
     */
    public int getSurfaceLogement() {
        return surfaceLogement;
    }

    /**
     * Sets surface logement.
     *
     * @param surfaceLogement the surface logement
     */
    public void setSurfaceLogement(final int surfaceLogement) {
        this.surfaceLogement = surfaceLogement;
    }

    /**
     * Gets hab agglo.
     *
     * @return the hab agglo
     */
    public int getHabAgglo() {
        return habAgglo;
    }

    /**
     * Sets hab agglo.
     *
     * @param habAgglo the hab agglo
     */
    public void setHabAgglo(final int habAgglo) {
        this.habAgglo = habAgglo;
    }

    public String getPossedeAppartement() {
        return possedeAppartement;
    }

    public void setPossedeAppartement(final String possedeAppartement) {
        this.possedeAppartement = possedeAppartement;
    }
}
