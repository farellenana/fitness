package com.example.fitness.controler;

import com.example.fitness.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private FileStorageService fileStorageService;

    @Autowired
    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "folder", required = false) String folder) {
        try {
            // Si aucun dossier n'est fourni, on utilise "default"
            folder = (folder != null && !folder.isEmpty()) ? folder : "default";

            // Stocker le fichier
            String fileName = fileStorageService.storeFile(file, folder);
            String fileUrl = "/uploads/" + fileName;

            return ResponseEntity.ok(new UploadResponse("Fichier uploadé avec succès dans '" + folder + "' : " + fileName, fileUrl));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new UploadResponse ("Erreur lors du téléchargement du fichier : " + e.getMessage(), null));
        }
    }

    private final String uploadDir = "/home/farelle/folder/";

    @GetMapping("/{folder}/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String folder, @PathVariable String filename) {
        try {
            Path filePath = fileStorageService.getFilePath(folder, filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            MediaType mediaType = MediaTypeFactory.getMediaType(filePath.toString()).orElse(MediaType.APPLICATION_OCTET_STREAM);
            return ResponseEntity.ok().contentType(mediaType).body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }




}


