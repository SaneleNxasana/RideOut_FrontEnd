package com.tp2assignment6.domaindesign.domain.factories.user;

import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class UserContactFactory {
    public static UserContact getContact(String contact, String value){
        return new UserContact.Builder()
                .contactType(contact)
                .contactValue(value)
                .build();
    }
}
