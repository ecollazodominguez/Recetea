package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.JWTUtil;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {


        @Autowired
        private UserServiceImp userServiceImp;

        @Autowired
        private JWTUtil jwtUtil;

        @PostMapping("")
        public String login(@RequestBody UserModel user) {

            UserModel userVerified = (userServiceImp.verifyCredentials((user)));
            if (userVerified != null){

                String JWToken = jwtUtil.create(String.valueOf(userVerified.getId()), userVerified.getEmail());
                return JWToken;

            };
            return "FALSE";
        }

}
