package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Objectifs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectifID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @Column(length = 50)
    private String nomObjectif; // Perte de poids, Gain musculaire, Endurance, etc.

    @Column(length = 500)
    private String descriptionObjectif;

    private Date dateDebut;
    private Date dateFin;

    public Long getObjectifID() {
        return objectifID;
    }

    public void setObjectifID(Long objectifID) {
        this.objectifID = objectifID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
