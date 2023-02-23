package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.Utils;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/all")
    public ArrayList<UserModel> getUsers(){
        return userServiceImp.getUsers();
    }

    @PostMapping("/add")
    public UserModel addUser(@RequestBody UserModel user){
        return userServiceImp.addUser(user);
    }
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id){
        return userServiceImp.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id ,@RequestBody UserModel user){
        Utils checkUser = new Utils();
        UserModel dbUser = userServiceImp.getUserById(id);
        dbUser = checkUser.getUpdatedUser(user, dbUser);
        return dbUser;
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userServiceImp.deleteUser(id);
        return "done";
    }


}
