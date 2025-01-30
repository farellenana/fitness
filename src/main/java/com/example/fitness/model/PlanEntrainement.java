package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntrainement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @Column(length = 100, nullable = false)
    private String nomPlan;

    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "objectifID")
    private Objectifs objectif;  // Relation vers l'entité Objectif
    // Perdre du poids, Prendre du muscle, etc.

    @Column(length = 500)
    private String description;

    public Long getPlanID() {
        return planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Objectifs getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectifs objectif) {
        this.objectif = objectif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
