package com.ku_26.bookstore.service; // Use your actual package name

import com.ku_26.bookstore.model.User;
import com.ku_26.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // --- START OF DIAGNOSTIC LOGGING ---
        System.out.println("==========================================================");
        System.out.println("Attempting to load user by username: '" + username + "'");

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            System.out.println("!!! User NOT FOUND in the database !!!");
            System.out.println("==========================================================");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();
        System.out.println(">>> User FOUND in the database.");
        System.out.println(">>> Database Password Hash: " + user.getPassword());
        System.out.println(">>> Database Role: " + user.getRole());
        // --- END OF DIAGNOSTIC LOGGING ---

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}