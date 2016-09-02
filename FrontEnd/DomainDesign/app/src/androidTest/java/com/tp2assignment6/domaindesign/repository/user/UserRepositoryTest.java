package com.tp2assignment6.domaindesign.repository.user;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.user.User;
import com.tp2assignment6.domaindesign.domain.repository.user.Impl.UserRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.user.UserRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class UserRepositoryTest extends AndroidTestCase {
    private static final String TAG = "USER TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        UserRepository repo = new UserRepositoryImpl(this.getContext());

        //CREATE
        User createItem = new User.Builder()
                .userID("8805040000003")
                .firstName("Peter")
                .lastName("Parker")
                .email("peter@parker.com")
                .build();
        User insertEntity = repo.create(createItem);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<User> users = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", users.size() > 0);

        //READ ITEM
        User item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        User updateItem = new User.Builder()
                .copy(item)
                .email("parker@web.com")
                .build();
        repo.update(updateItem);
        User newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "parker@web.com", newItem.getEmail());

        //DELETE
        repo.delete(updateItem);
        User deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
