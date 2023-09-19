package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.JWTUtil;
import com.receteausersservice.receteausersservice.Utils.Utils;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean tokenValidate(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @GetMapping("/all")
    public ArrayList<UserModel> getUsers(@RequestHeader(value="Authorization") String token){
        if(!tokenValidate(token)) return null;

        return userServiceImp.getUsers();
    }

    @PostMapping("/add")
    public UserModel addUser(@RequestBody UserModel user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPassword= argon2.hash(1,1024,1,user.getPassword());
        user.setPassword(hashPassword);

        return userServiceImp.addUser(user);
    }
    @GetMapping("/{id}")
    public UserModel getUserById(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!tokenValidate(token)) return null;

        return userServiceImp.getUserById(id);
    }
    @PutMapping("/{id}")
    public String updateUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id ,@RequestBody UserModel user){
        if(!tokenValidate(token)) return null;

        Utils userUtils = new Utils();
        UserModel mergedUser;

        UserModel dbUser = userServiceImp.getUserById(id);
        mergedUser = userUtils.mergeUser(user, dbUser);
        userServiceImp.updateUser(mergedUser);
        return "updated";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!tokenValidate(token)) return null;

        userServiceImp.deleteUser(id);
        return "done";
    }


}
