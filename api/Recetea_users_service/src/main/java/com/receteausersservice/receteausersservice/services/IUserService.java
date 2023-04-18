package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<UserModel> getUsers();
    UserModel getUserById(Long Id);

    UserModel addUser(UserModel user);

    void deleteUser(Long Id);

    void updateUser(UserModel user);

    UserModel verifyCredentials(UserModel user);
}
