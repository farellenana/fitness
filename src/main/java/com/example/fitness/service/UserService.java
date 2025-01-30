package com.example.fitness.service;

import com.example.fitness.dto.UserDTO;
import com.example.fitness.mapper.UserMapper;
import com.example.fitness.model.User;
import com.example.fitness.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;  // Ajout du mapper

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;  // Injection du mapper
    }

    // Inscription d'un utilisateur et retour d'un DTO
    public UserDTO registerUser(UserDTO userDTO) {
        // Vérification si l'email existe déjà
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Conversion de UserDTO en User (entité)
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hachage du mot de passe

        // Sauvegarde dans la base de données
        User savedUser = userRepository.save(user);

        // Retourner un DTO après l'ajout
        return userMapper.toUserDTO(savedUser);
    }

    // Recherche d'un utilisateur par son email et retour d'un DTO
    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::toUserDTO);  // Conversion de l'entité en DTO
    }

    // Charger un utilisateur par son nom d'utilisateur (email) pour Spring Security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();
    }







//================================ code initiale =========================================

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    public User registerUser(User user) {
//        // Vérification si l'email existe déjà
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("Email already in use");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hachage du mot de passe
//        return userRepository.save(user);
//    }
//
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//                .authorities(user.getRoles().stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList()))
//                .build();
//    }

}
