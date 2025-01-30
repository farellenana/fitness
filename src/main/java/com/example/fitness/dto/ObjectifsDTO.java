package com.example.fitness.dto;

import java.util.Date;

public class ObjectifsDTO {
    private Long objectifID;
    private Long userID; // On stocke seulement l'ID de l'utilisateur
    private String nomObjectif;
    private String descriptionObjectif;
    private Date dateDebut;
    private Date dateFin;

    public Long getObjectifID() {
        return objectifID;
    }

    public void setObjectifID(Long objectifID) {
        this.objectifID = objectifID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getNomObjectif() {
        return nomObjectif;
    }

    public void setNomObjectif(String nomObjectif) {
        this.nomObjectif = nomObjectif;
    }

    public String getDescriptionObjectif() {
        return descriptionObjectif;
    }

    public void setDescriptionObjectif(String descriptionObjectif) {
        this.descriptionObjectif = descriptionObjectif;
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
}
