package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Transport.
 */
@Entity
@Table(name = "transport")
public class Transport {

    @Id
    private int annee;

    @Id
    private int semaine;

    @Id
    private int id;

    @Column(name = "total_transport")
    private int totalTransport;

    /**
     * Instantiates a new Transport.
     */
    public Transport() {

    }

    /**
     * Instantiates a new Transport.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     */
    public Transport(final int annee, final int semaine, final int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    /**
     * Instantiates a new Transport.
     *
     * @param annee             the annee
     * @param semaine           the semaine
     * @param id                the id
     * @param totalTransport the total transport
     */
    public Transport(final int annee, final int semaine, final int id,
                        final int totalTransport) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
        this.totalTransport = totalTransport;
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
     * Gets total transport.
     *
     * @return the total transport
     */
    public int getTotalTransport() {
        return totalTransport;
    }

    /**
     * Sets total transport.
     *
     * @param totalTransport the total transport
     */
    public void setTotalTransport(final int totalTransport) {
        this.totalTransport = totalTransport;
    }
}
