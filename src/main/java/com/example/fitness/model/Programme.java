package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.Utilities;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programmeID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(length = 100, nullable = false)
    private String nomProgramme; // Nom du programme (ex: "Programme Perte de Poids", "Programme Musculation")

    @Column(length = 500)
    private String description; // Description du programme

    @Column(length = 50)
    private String typeObjectif; // Perte de poids, Gain musculaire, Endurance, etc.

    private Date dateDebut;
    private Date dateFin;

    private boolean estActif;

    public Long getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Long programmeID) {
        this.programmeID = programmeID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getTypeObjectif() {
        return typeObjectif;
    }

    public void setTypeObjectif(String typeObjectif) {
        this.typeObjectif = typeObjectif;
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
}
