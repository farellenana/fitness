package com.example.fitness.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

public class ProgrammeDTO {

    private Long programmeID;
    private Long userID;
    private Long objectifID;
    private String nomProgramme;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private boolean estActif;


    public Long getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Long programmeID) {
        this.programmeID = programmeID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public Long getObjectifID() {
        return objectifID;
    }

    public void setObjectifID(Long objectifID) {
        this.objectifID = objectifID;
    }
}
