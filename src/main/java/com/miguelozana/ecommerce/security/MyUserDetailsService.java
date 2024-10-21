package com.miguelozana.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.miguelozana.ecommerce.models.Users;
import com.miguelozana.ecommerce.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Users user = userRepository.findByUsername(username);
      if (user == null) {
        throw new UsernameNotFoundException("User not found");
      }
      return new MyUserDetails(user);
    }

    
}
