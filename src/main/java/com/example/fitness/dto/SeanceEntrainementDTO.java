package com.example.fitness.dto;

import java.util.Date;
import java.util.List;

public class SeanceEntrainementDTO {
    private Long seanceID;
    private Long userID;
    private Long planID;
    private Date dateSeance;
    private String typeSeance;
    private int duree;
    private double caloriesBrulees;
    private String commentaires;
    private List<ExerciceDTO> exercices;

    public Long getSeanceID() {
        return seanceID;
    }

    public void setSeanceID(Long seanceID) {
        this.seanceID = seanceID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPlanID() {
        return planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
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

    public List<ExerciceDTO> getExercices() {
        return exercices;
    }

    public void setExercices(List<ExerciceDTO> exercices) {
        this.exercices = exercices;
    }
}
