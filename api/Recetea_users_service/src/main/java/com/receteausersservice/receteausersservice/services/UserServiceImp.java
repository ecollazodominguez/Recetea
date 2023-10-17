package com.receteausersservice.receteausersservice.services;

import com.receteausersservice.receteausersservice.dto.UserRequest;
import com.receteausersservice.receteausersservice.dto.UserResponse;
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

    public List<UserResponse> getUsers(){
        List<UserModel> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();

    }

    private UserResponse mapToUserResponse(UserModel user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .diet(user.getDiet())
                .favorite_recipes((user.getFavorite_recipes()))
                .build();
    }


    @Override
    public UserResponse getUserById(Long Id) {

        UserModel user = userRepository.getReferenceById(Id);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .diet(user.getDiet())
                .favorite_recipes((user.getFavorite_recipes()))
                .build();

    }

    @Override
    public Boolean addUser(UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserModel newUser = UserModel.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .diet(user.getDiet())
                .build();

        userRepository.save(newUser);
        return true;
    }
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public UserResponse updateUser(UserResponse user, Long id) {

        UserModel dbUser = userRepository.getReferenceById(id);
        dbUser = mergeUser(user, dbUser);
        userRepository.save(dbUser);

        return UserResponse.builder()
                .id(dbUser.getId())
                .name(dbUser.getName())
                .email(dbUser.getEmail())
                .diet(dbUser.getDiet())
                .favorite_recipes(dbUser.getFavorite_recipes())
                .build();
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

    //Method to merge the DBUser and requestUser from data sent from frontend.
    // If the data sent (requestUser) has any attribute value. It will be assigned to DBUser, if there is no value, will stay the same.
    public UserModel mergeUser(UserResponse requestUser, UserModel dbUser){

        dbUser.setName((requestUser.getName() != null) ? requestUser.getName() : dbUser.getName());
        dbUser.setEmail((requestUser.getEmail() != null) ? requestUser.getEmail() : dbUser.getEmail());

        return dbUser;

    }
}
