package com.miguelozana.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelozana.ecommerce.dto.UserDTO;
import com.miguelozana.ecommerce.models.Users;
import com.miguelozana.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    // Add methods for CRUD operations on users
    // Use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping annotations for HTTP requests
    // Implement business logic for each method to interact with the UserService
    // Return appropriate HTTP status codes and responses
    // Use DTOs (Data Transfer Objects) to transfer data between the controller and the service
    // Add error handling for invalid requests and database errors
    // Use Spring Security for authentication and authorization
    // Implement pagination and sorting for listing users

    @Autowired
    private UserService userService;



    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return users.stream()
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/sign-up")
    public UserDTO createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return new UserDTO(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        Users userUpdate = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(new UserDTO(userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
