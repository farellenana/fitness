package com.example.fitness.model;

import jakarta.persistence.*;


import java.util.Date;

public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProgressionID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    private Date date;

    private double poids;
    private double pourcentageGraisseCorporelle;
    private double masseMusculaire;
    private double tourPoitrine;
    private double tourTaille;
    private double tourHanches;

    @Override
    public String toString() {
        return "Progression{" +
                "ProgressionID=" + ProgressionID +
                ", user=" + user +
                ", date=" + date +
                ", poids=" + poids +
                ", pourcentageGraisseCorporelle=" + pourcentageGraisseCorporelle +
                ", masseMusculaire=" + masseMusculaire +
                ", tourPoitrine=" + tourPoitrine +
                ", tourTaille=" + tourTaille +
                ", tourHanches=" + tourHanches +
                '}';
    }
}
