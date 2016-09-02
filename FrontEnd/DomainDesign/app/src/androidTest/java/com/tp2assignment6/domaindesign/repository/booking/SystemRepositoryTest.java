package com.tp2assignment6.domaindesign.repository.booking;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.booking.System;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.SystemRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.SystemRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SystemRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SYSTEM TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        SystemRepository repo = new SystemRepositoryImpl(this.getContext());

        //CREATE
        System createItem = new System.Builder()
                .userDetails("850205 000000 2, Peter, Parker, peter@parker.test")
                .reservationDetails("06 May 2016, 9")
                .build();
        System insertEntity = repo.create(createItem);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<System> systems = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", systems.size() > 0);

        //READ ITEM
        System item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        System updateItem = new System.Builder()
                .copy(item)
                .reservationDetails("08 May 2016, 3")
                .build();
        repo.update(updateItem);
        System newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "08 May 2016, 3", newItem.getReservationDetails());

        //DELETE ITEM
        repo.delete(updateItem);
        System deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
