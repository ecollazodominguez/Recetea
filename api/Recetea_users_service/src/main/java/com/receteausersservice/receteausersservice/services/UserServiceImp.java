package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }


    @Override
    public UserModel getUserById(Long Id) {
        return userRepository.getReferenceById(Id);
    }

    @Override
    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}
