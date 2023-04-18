package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;

import java.util.ArrayList;

public interface IUserService {
    public ArrayList<UserModel> getUsers();
    public UserModel getUserById(Long Id);

    public UserModel addUser(UserModel user);

    public void deleteUser(Long Id);

    public void updateUser(UserModel user);
}
