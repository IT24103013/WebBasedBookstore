package com.ku_26.bookstore.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Get the roles of the authenticated user.
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Check the role and redirect accordingly.
        if (roles.contains("ROLE_DELIVERY_STAFF")) {
            response.sendRedirect("/delivery-staff/dashboard");
        } else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/dashboard"); // Example for another role
        } else {
            // Default redirect if the role doesn't match any specific case.
            response.sendRedirect("/");
        }
    }
}