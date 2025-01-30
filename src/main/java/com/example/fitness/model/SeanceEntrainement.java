package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeanceEntrainement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seanceID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "planID")
    private PlanEntrainement planEntrainement; // Relation avec le plan d'entraînement

    private Date dateSeance;

    @Column(length = 50)
    private String typeSeance; // Cardio, Musculation, Yoga, etc.

    private int duree; // en minutes
    private double caloriesBrulees;

    @Column(length = 500)
    private String commentaires;

    @OneToMany(mappedBy = "seanceEntrainement", cascade = CascadeType.ALL)
    private List<Exercice> exercices; // Liste d'exercices pour la séance

    public Long getSeanceID() {
        return seanceID;
    }

    public void setSeanceID(Long seanceID) {
        this.seanceID = seanceID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlanEntrainement getPlanEntrainement() {
        return planEntrainement;
    }

    public void setPlanEntrainement(PlanEntrainement planEntrainement) {
        this.planEntrainement = planEntrainement;
    }

    public Date getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getCaloriesBrulees() {
        return caloriesBrulees;
    }

    public void setCaloriesBrulees(double caloriesBrulees) {
        this.caloriesBrulees = caloriesBrulees;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}
