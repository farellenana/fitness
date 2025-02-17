package com.example.fitness.controler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UploadResponse {
    private String message;
    private String fileName;

    public UploadResponse(String message, String fileName) {
        this.message = message;
        this.fileName = fileName;
    }
}
