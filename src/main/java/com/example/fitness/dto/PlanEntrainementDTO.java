package com.example.fitness.dto;

import com.example.fitness.model.User;
import jakarta.persistence.*;

import java.util.Date;

public class PlanEntrainementDTO {

    private Long planID;
    private Long userID;
    private String nomPlan;
    private Date dateDebut;
    private Date dateFin;
    private Long objectifID;  // ID de l'objectif associé
    private String description;

    public Long getPlanID() {
        return planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getNomPlan() {
        return nomPlan;
    }

    public void setNomPlan(String nomPlan) {
        this.nomPlan = nomPlan;
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

    public Long getObjectifID() {
        return objectifID;
    }

    public void setObjectifID(Long objectifID) {
        this.objectifID = objectifID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
