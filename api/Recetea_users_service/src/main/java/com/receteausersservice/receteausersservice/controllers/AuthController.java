package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {


        @Autowired
        private UserServiceImp userServiceImp;

        @PostMapping("")
        public String login(@RequestBody UserModel user) {
            if (userServiceImp.verifyCredentials(user)){
                    return "OK";
            };
            return "FALSE";
        }

}
