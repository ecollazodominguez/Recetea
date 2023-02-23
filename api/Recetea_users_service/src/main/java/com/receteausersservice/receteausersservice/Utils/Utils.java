package com.receteausersservice.receteausersservice.Utils;

import com.receteausersservice.receteausersservice.models.UserModel;

public class Utils {

//Method to update the DBUser from data sent from frontend.
    // If the data sent (requestUser) has any attribute value. It will be assigned to the DBUser, if there is no attribute, value will stay the same.
    public UserModel getUpdatedUser(UserModel requestUser, UserModel dbUser){
        if (requestUser.getName() != null) {
            dbUser.setName(requestUser.getName());
        }
        if (requestUser.getEmail() != null) {
            dbUser.setEmail(requestUser.getEmail());
        }

        return dbUser;

    }
}
