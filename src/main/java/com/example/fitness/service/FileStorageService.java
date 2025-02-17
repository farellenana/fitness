//package com.example.fitness.service;
//
//
//import com.example.fitness.security.FileStorageProperties;
//import lombok.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@Service
//public class FileStorageService {
//    private final Path fileStorageLocation;
//
//    // Injection du chemin d'upload
//    public FileStorageService(FileStorageProperties fileStorageProperties) {
//        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new RuntimeException("Impossible de créer le répertoire d'upload", ex);
//        }
//    }
//
//    // Stocker le fichier et renvoyer son nom
//    public String storeFile(MultipartFile file, String folder) {
//        if (file.isEmpty()) {
//            throw new RuntimeException("Le fichier est vide !");
//        }
//
//        try {
//            // Créer un nom unique
//            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//            Path targetLocation = this.fileStorageLocation.resolve(folder).resolve(fileName);
//
//            // Vérifier si le dossier existe, sinon le créer
//            Files.createDirectories(this.fileStorageLocation.resolve(folder));
//
//            // Sauvegarder le fichier
//               Files.copy(file.getInputStream(), targetLocation);
//
//            return fileName; // Retourne le nom du fichier stocké
//        } catch (IOException ex) {
//            throw new RuntimeException("Échec de l'upload du fichier", ex);
//        }
//    }
//}




package com.example.fitness.service;

import com.example.fitness.security.FileStorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Impossible de créer le répertoire d'upload", ex);
        }
    }

    public Path getFilePath(String folder, String filename) {
        return this.fileStorageLocation.resolve(folder).resolve(filename).normalize();
    }


    public String storeFile(MultipartFile file, String folder) {
        if (file.isEmpty()) {
            throw new RuntimeException("Le fichier est vide !");
        }

        try {
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "_");

            // Définir le chemin du dossier cible
            Path folderPath = this.fileStorageLocation.resolve(folder).normalize();

            // Vérifier et créer le dossier si nécessaire
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // Définir le chemin du fichier final
            Path targetLocation = folderPath.resolve(fileName);

            // Copier le fichier en remplaçant l'existant si nécessaire
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return folder + "/" + fileName; // Retourner le chemin relatif du fichier
        } catch (IOException ex) {
            throw new RuntimeException("Échec de l'upload du fichier", ex);
        }
    }
}
