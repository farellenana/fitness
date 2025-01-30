package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long abonnementID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(length = 20)
    private String typeAbonnement; // Mensuel, Annuel, etc.

    private Date dateDebut;
    private Date dateFin;

    @Column(length = 20)
    private String statut; // Actif, Expiré


}
