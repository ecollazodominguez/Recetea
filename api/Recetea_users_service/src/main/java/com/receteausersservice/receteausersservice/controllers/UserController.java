package com.receteausersservice.receteausersservice.controllers;

import com.receteausersservice.receteausersservice.Utils.Utils;
import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.services.JwtService;
import com.receteausersservice.receteausersservice.services.UserModelDetails;
import com.receteausersservice.receteausersservice.services.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        ArrayList<UserModel> users = userServiceImp.getUsers();

        if (users == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users Found");

        return users;


    }

    @PostMapping("/add")
    public UserModel addUser(@RequestBody UserModel user){

        if (user.getEmail() == null || user.getPassword() == null || user.getName() == null || user.getDiet() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some information is missing");
        }
        try{
            return userServiceImp.addUser(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This email already exists");
        }

    }
    @GetMapping("/{id}")
    public UserModel getUserById(@RequestHeader(value="Authorization") String token, @PathVariable Long id){

        try {
            return userServiceImp.getUserById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user with that ID");

        }
    }
    @PutMapping("/{id}")
    public UserModel updateUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id ,@RequestBody UserModel user, HttpServletRequest request){
        try {
            token = token.substring(7);
            UserModel requestUser = userServiceImp.getUserById(id);

            UserModelDetails userModelDetails = userServiceImp.loadUserByUsername(requestUser.getEmail());
            if (jwtService.validateToken(token, userModelDetails)) {
                Utils userUtils = new Utils();
                UserModel dbUser = userServiceImp.getUserById(id);
                requestUser = userUtils.mergeUser(user, dbUser);

                return userServiceImp.updateUser(requestUser);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your ID doesn't match with this one");
            }
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your ID doesn't match with this one");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        try {
            token = token.substring(7);
            UserModel requestUser = userServiceImp.getUserById(id);
            UserModelDetails userModelDetails = userServiceImp.loadUserByUsername(requestUser.getEmail());
            if (jwtService.validateToken(token, userModelDetails)) {
                userServiceImp.deleteUser(id);
                return ResponseEntity.ok().body("User with ID: " +id+ " deleted.");
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your ID doesn't match with this one");
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your ID doesn't match with this one");
        }
    }


}
