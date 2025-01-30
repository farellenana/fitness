package com.example.fitness.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(length = 500)
    private String message;

    private Date date;

    private boolean estLue;
}
