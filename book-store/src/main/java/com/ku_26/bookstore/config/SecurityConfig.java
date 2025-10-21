package com.ku_26.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permit access to the login page and static resources for everyone
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        // Secure the delivery staff pages
                        .requestMatchers("/delivery-staff/**").hasRole("DELIVERY_STAFF")
                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // This is the page users will see
                        .loginPage("/login")

                        // THIS IS A KEY CHANGE: We tell Spring Security to listen for a POST on /perform_login
                        .loginProcessingUrl("/perform_login")

                        // This is where to go on successful login
                        .defaultSuccessUrl("/delivery-staff/dashboard", true)

                        // This is where to go if login fails
                        .failureUrl("/login?error=true")

                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}