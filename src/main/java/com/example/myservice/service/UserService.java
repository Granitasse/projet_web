package com.example.myservice.service;

import com.example.myservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("coucou");
        var user = userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        String[] authorities = user.isAdmin() ? new String[]{"ADMIN"} : new String[0];

        return User.builder()
                   .username(user.getUsername())
                   .password(user.getPassword())
                   .authorities(authorities)
                   .build();
    }
}