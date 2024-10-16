package com.miguelozana.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.models.Users;
import com.miguelozana.ecommerce.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public Users createUser(Users user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email alredy in use! Please select a different email");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username already in use! Please select a different username");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users userDetails ) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }
}
