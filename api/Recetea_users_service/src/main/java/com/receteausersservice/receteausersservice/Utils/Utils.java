package com.receteausersservice.receteausersservice.Utils;

import com.receteausersservice.receteausersservice.models.UserModel;

public class Utils {

//Method to merge the DBUser and requestUser from data sent from frontend.
    // If the data sent (requestUser) has any attribute value. It will be assigned to DBUser, if there is no value, will stay the same.
    public UserModel mergeUser(UserModel requestUser, UserModel dbUser){

        dbUser.setName((requestUser.getName() != null) ? requestUser.getName() : dbUser.getName());
        dbUser.setEmail((requestUser.getEmail() != null) ? requestUser.getEmail() : dbUser.getEmail());

        return dbUser;

    }
}
