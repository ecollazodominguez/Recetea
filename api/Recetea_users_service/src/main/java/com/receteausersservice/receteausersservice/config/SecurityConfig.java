package com.receteausersservice.receteausersservice.config;

import com.receteausersservice.receteausersservice.filter.JwtAuthFilter;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity
    public class SecurityConfig {

        // User Creation
        @Bean
        public JwtAuthFilter authFilter() {return new JwtAuthFilter();}

        // User Creation
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserServiceImp();
        }

        // Configuring HttpSecurity
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.csrf().disable()
                        .sessionManagement()
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and()
                        .authorizeHttpRequests()
                            .requestMatchers("/api/login", "/api/user/add","/error").permitAll()
                            .anyRequest().authenticated()
                            .and()
                        .authenticationProvider(authenticationProvider())
                            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

        // Password Encoding
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new Argon2PasswordEncoder(16, 32, 1, 16384, 2);
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService());
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }


    }

