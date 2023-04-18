package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.Utils;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/")
    public ArrayList<UserModel> login(){
        return userServiceImp.getUsers();
    }

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
    public String updateUser(@PathVariable Long id ,@RequestBody UserModel user){
        Utils userUtils = new Utils();
        UserModel mergedUser;

        UserModel dbUser = userServiceImp.getUserById(id);
        mergedUser = userUtils.mergeUser(user, dbUser);
        userServiceImp.updateUser(mergedUser);
        return "updated";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userServiceImp.deleteUser(id);
        return "done";
    }


}
