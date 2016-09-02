package com.tp2assignment6.domaindesign.repository.user;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;
import com.tp2assignment6.domaindesign.domain.repository.user.Impl.UserContactRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.user.UserContactRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class UserContactRepositoryTest extends AndroidTestCase {
    private static final String TAG = "USER CONTACT TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        UserContactRepository repo = new UserContactRepositoryImpl(this.getContext());

        //CREATE
        UserContact createEntity = new UserContact.Builder()
                .contactType("Cellphone")
                .contactValue("073 456 9021")
                .build();
        UserContact insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<UserContact> userContacts = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", userContacts.size() > 0);

        //READ ITEM
        UserContact item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        UserContact updateItem = new UserContact.Builder()
                .copy(item)
                .contactValue("071 808 6142")
                .build();
        repo.update(updateItem);
        UserContact newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "071 808 6142", newItem.getContactValue());

        //DELETE ITEM
        repo.delete(updateItem);
        UserContact deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
