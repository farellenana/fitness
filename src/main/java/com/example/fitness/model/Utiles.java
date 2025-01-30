package com.example.fitness.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Utiles {
    private static AtomicInteger counter = new AtomicInteger(1);

    public static String generateID(String prefix) {
        // Utilise un compteur qui s'incrémente à chaque appel
        return prefix + counter.getAndIncrement();
    }
}
