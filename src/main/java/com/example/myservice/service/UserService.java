package com.example.myservice.service;

import com.example.myservice.controller.dto.UserDTO;
import com.example.myservice.repository.UserRepository;
import com.example.myservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public void newUser(User user) {
        if (userExist(user)) {
            throw new IllegalArgumentException("Utilisateur déjà existant");
        }
        if (user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Mot de passe vide");
        }
        user.setAdmin(false);
        saveUser(user);
    }

    public boolean userExist(User user) {
        return userRepository.getUserByUsername(user.getUsername()).isPresent();
    }

    private void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public void updateUser(User user){
        User dbUser = userRepository.getUserByUsername(user.getUsername()).orElse(user);
        dbUser.setPassword(user.getPassword());
        dbUser.setAdmin(user.isAdmin());
        saveUser(dbUser);
    }

    public List<UserDTO> listUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getUsername(),user.isAdmin())).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.getUserByUsername(username)
                                 .orElseThrow(() -> new UsernameNotFoundException(username));

        String[] authorities = user.isAdmin() ? new String[]{"ADMIN"} : new String[0];

        return org.springframework.security.core.userdetails.User.builder()
                                                                 .username(user.getUsername())
                                                                 .password(user.getPassword())
                                                                 .authorities(authorities)
                                                                 .build();
    }
}