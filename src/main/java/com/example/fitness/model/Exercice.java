package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Exercice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long exerciceID;

        @Column(length = 150, nullable = false)
        private String nomExercice;

        @ManyToOne
        @JoinColumn(name = "categoryExID", nullable = false)
        private CategoryExercice categoryExercice; // Association avec la catégorie

        @ManyToOne
        @JoinColumn(name = "seanceID") // Nom de la colonne dans la table Exercice
        private SeanceEntrainement seanceEntrainement; // Référence à la séance d'entraînement

        @Column(length = 500)
        private String description;

        private int repetitions; // Nombre de répétitions
        private int series; // Nombre de séries
        private String urlVideo;
        private String urlImage;




        // Getters et Setters


        public Long getExerciceID() {
                return exerciceID;
        }

        public void setExerciceID(Long exerciceID) {
                this.exerciceID = exerciceID;
        }

        public String getNomExercice() {
                return nomExercice;
        }

        public void setNomExercice(String nomExercice) {
                this.nomExercice = nomExercice;
        }

        public CategoryExercice getCategoryExercice() {
                return categoryExercice;
        }

        public void setCategoryExercice(CategoryExercice categoryExercice) {
                this.categoryExercice = categoryExercice;
        }

        public SeanceEntrainement getSeanceEntrainement() {
                return seanceEntrainement;
        }

        public void setSeanceEntrainement(SeanceEntrainement seanceEntrainement) {
                this.seanceEntrainement = seanceEntrainement;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
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
}
