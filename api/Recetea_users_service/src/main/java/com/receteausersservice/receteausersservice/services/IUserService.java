package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.dto.UserRequest;
import com.receteausersservice.receteausersservice.dto.UserResponse;
import com.receteausersservice.receteausersservice.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserResponse> getUsers();
    UserResponse getUserById(Long Id);

    Boolean addUser(UserRequest user);

    void deleteUser(Long Id);

    UserResponse updateUser(UserResponse user, Long id);

    Optional<UserModel> getUserByEmail(String email);

    //UserModel verifyCredentials(UserModel user);
}
