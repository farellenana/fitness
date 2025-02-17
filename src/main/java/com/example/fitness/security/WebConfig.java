package com.example.fitness.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/farelle/folder/");  // Répertoire où les fichiers sont stockés
        // Accès aux images
        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:/home/farelle/folder/images/");

        // Accès aux vidéos
        registry.addResourceHandler("/uploads/videos/**")
                .addResourceLocations("file:/home/farelle/folder/videos/");
    }
}
