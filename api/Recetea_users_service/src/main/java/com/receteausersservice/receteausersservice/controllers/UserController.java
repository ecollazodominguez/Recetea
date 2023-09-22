package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.Utils;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.JwtService;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/all")
    public ArrayList<UserModel> getUsers(@RequestHeader(value="Authorization") String token){
        System.out.println("asdas");

        return userServiceImp.getUsers();
    }

    @PostMapping("/add")
    public UserModel addUser(@RequestBody UserModel user){

        return userServiceImp.addUser(user);
    }
    @GetMapping("/{id}")
    public UserModel getUserById(@RequestHeader(value="Authorization") String token, @PathVariable Long id){

        return userServiceImp.getUserById(id);
    }
    @PutMapping("/{id}")
    public String updateUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id ,@RequestBody UserModel user){

        Utils userUtils = new Utils();
        UserModel mergedUser;

        UserModel dbUser = userServiceImp.getUserById(id);
        mergedUser = userUtils.mergeUser(user, dbUser);
        userServiceImp.updateUser(mergedUser);
        return "updated";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id){

        userServiceImp.deleteUser(id);
        return "done";
    }


}
