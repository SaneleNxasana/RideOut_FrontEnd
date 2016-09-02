package com.tp2assignment6.domaindesign.factories.user;

import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;
import com.tp2assignment6.domaindesign.domain.factories.user.UserContactFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class UserContactFactoryTest {
    @Test
    public void testCreate() throws Exception {
        UserContact userContact = UserContactFactory.getContact("Cellphone", "0718524608");
        Assert.assertEquals("Cellphone",userContact.getContactType());
    }

    @Test
    public void testUpdate() throws Exception {
        UserContact userContact = UserContactFactory.getContact("Cellphone", "0718524608");
        UserContact newUserContact = new UserContact
                .Builder()
                .copy(userContact)
                .contactType("Email")
                .build();
        Assert.assertEquals("Email", newUserContact.getContactType());

    }
}
