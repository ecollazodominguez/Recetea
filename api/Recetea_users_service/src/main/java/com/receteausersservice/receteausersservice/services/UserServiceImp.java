package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.models.UserModel;
import com.receteausersservice.receteausersservice.repositories.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService, UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModelDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            Optional<UserModel> userDetail = userServiceImp.getUserByEmail(email);

            // Converting userDetail to UserDetails
            return userDetail.map(UserModelDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
    }

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }


    @Override
    public UserModel getUserById(Long Id) {
        return userRepository.getReferenceById(Id);
    }

    @Override
    public UserModel addUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        String query = "FROM UserModel WHERE email = :email";
        List<UserModel> userList = entityManager.createQuery(query)
                .setParameter("email", email)
                .getResultList();

        if (userList.isEmpty()) return null;

        return Optional.of(userList.get(0));
    }
    /**
    @Override

         public UserModel verifyCredentials(UserModel user) {
        String query = "FROM UserModel WHERE email = :email";
        List<UserModel> userList = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (userList.isEmpty()) return null;

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if(argon2.verify(userList.get(0).getPassword(), user.getPassword())) return userList.get(0);

        return null;


    }
     */


}
