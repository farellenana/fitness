package com.example.fitness.dto;

import jakarta.persistence.Column;

public class ExerciceDTO {

    private Long exerciceID;
    private Long categoryExID;
    private Long seanceID;
    private String nomExercice;
    private String description;
    private String urlVideo;
    private String urlImage;
    private int repetitions; // Nombre de répétitions
    private int series; // Nombre de séries

    public Long getExerciceID() {
        return exerciceID;
    }

    public void setExerciceID(Long exerciceID) {
        this.exerciceID = exerciceID;
    }

    public Long getCategoryExID() {
        return categoryExID;
    }

    public void setCategoryExID(Long categoryExID) {
        this.categoryExID = categoryExID;
    }

    public Long getSeanceID() {
        return seanceID;
    }

    public void setSeanceID(Long seanceID) {
        this.seanceID = seanceID;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
