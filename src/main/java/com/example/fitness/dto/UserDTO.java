package com.example.fitness.dto;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String fullName; // Prénom et nom combinés
    private String birthDate;
    private String email;
    private String password;
    private String gender;
    private double height;
    private double weight;
    private String fitnessLevel;
    private String profilePicture;
    private String role;
    private Set<String> roles;
    private Date createdAt;
    private Date updatedAt;

    // Constructeur par défaut
    public UserDTO() {}

    // Constructeur avec tous les champs

    public UserDTO(Long id, String fullName, String birthDate, String email, String password, String gender, double height, double weight, String fitnessLevel, String profilePicture, String role, Set<String> roles, Date createdAt, Date updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.fitnessLevel = fitnessLevel;
        this.profilePicture = profilePicture;
        this.role = role;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // Getters et Setters manuels

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
