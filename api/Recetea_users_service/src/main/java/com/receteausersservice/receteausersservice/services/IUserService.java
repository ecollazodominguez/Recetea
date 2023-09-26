package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IUserService {
    ArrayList<UserModel> getUsers();
    UserModel getUserById(Long Id);

    UserModel addUser(UserModel user);

    void deleteUser(Long Id);

    UserModel updateUser(UserModel user);

    Optional<UserModel> getUserByEmail(String email);

    //UserModel verifyCredentials(UserModel user);
}
