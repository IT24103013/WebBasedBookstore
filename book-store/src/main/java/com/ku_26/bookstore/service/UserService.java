package com.ku_26.bookstore.service;

import com.ku_26.bookstore.model.User;

public interface UserService {
    /**
     * Finds a User entity by its username.
     *
     * @param username the username to search for.
     * @return the User entity.
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException if the user is not found.
     */
    User findByUsername(String username);
}
