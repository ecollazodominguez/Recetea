package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.models.AuthRequest;

import com.receteausersservice.receteausersservice.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


        @PostMapping("")
        public String login(@RequestBody AuthRequest authRequest) {
            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
                if (authentication.isAuthenticated()) {
                    return jwtService.generateToken(authRequest.getEmail());
                } else {
                    throw new UsernameNotFoundException("invalid user request !");
                }
            }catch (Exception e){
                System.out.println("Authentication failed: " + e.getMessage());
                throw new UsernameNotFoundException("Authentication failed!");
            }
        }

}
