package com.miguelozana.ecommerce.controller;

import com.miguelozana.ecommerce.models.Users;
import com.miguelozana.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/sign-up")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        Users userUpdate = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(userUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
