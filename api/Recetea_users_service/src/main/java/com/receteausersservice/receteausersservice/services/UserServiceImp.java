package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.repositories.IUserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    @Override
    public void updateUser(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public boolean verifyCredentials(UserModel user) {
        String query = "FROM UserModel WHERE email = :email";
        List<UserModel> userList = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (userList.isEmpty()) return false;

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(userList.get(0).getPassword(), user.getPassword());

    }


}
