package com.tp2assignment6.domaindesign.domain.services.user;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface UserContactService {
    void addUserContact(Context context, UserContact contact);
    void updateUserContact(Context context, UserContact contact);
}
