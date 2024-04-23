package fr.univlyon1.m1if10.bilanCo2.model;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;

public class Ident implements Serializable {

    private int annee;

    private int semaine;

    private int id;

    public Ident(int annee, int semaine, int id) {
        this.annee = annee;
        this.semaine = semaine;
        this.id = id;
    }

    public Ident() {
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
