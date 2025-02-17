package com.example.fitness.dto;

public class CategoryExerciceDTO {
    private Long categoryID;
    private String nom;


    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
