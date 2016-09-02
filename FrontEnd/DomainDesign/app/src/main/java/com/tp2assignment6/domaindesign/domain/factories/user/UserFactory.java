package com.tp2assignment6.domaindesign.domain.factories.user;

import com.tp2assignment6.domaindesign.domain.domain.user.User;

import java.util.Date;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class UserFactory {
    public static User getUser(String uID, String firstname, String lastname, String mail){
        return new User.Builder()
                .userID(uID)
                .firstName(firstname)
                .lastName(lastname)
                .email(mail)
                .build();
    }
}
