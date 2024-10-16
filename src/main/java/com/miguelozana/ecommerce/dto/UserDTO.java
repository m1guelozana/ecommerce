package com.miguelozana.ecommerce.dto;

import com.miguelozana.ecommerce.models.Users;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDTO(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        
    }
}
