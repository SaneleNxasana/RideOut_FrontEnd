package com.tp2assignment6.domaindesign.repository.vehicle;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Coupe;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.CoupeRepository;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.CoupeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class CoupeRepositoryTest extends AndroidTestCase {
    private static final String TAG = "COUPE TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        CoupeRepository repo = new CoupeRepositoryImpl(this.getContext());

        //CREATE
        Coupe createEntity = new Coupe.Builder()
                .registrationNumber("CA 9621")
                .numberSeats("2")
                .build();
        Coupe insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Coupe> coupes = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", coupes.size() > 0);

        //READ ITEM
        Coupe item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Coupe updateItem = new Coupe.Builder()
                .copy(item)
                .registrationNumber("CA 7423")
                .build();
        repo.update(updateItem);
        Coupe newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "CA 7423", newItem.getRegistrationNumber());

        //DELETE ITEM
        repo.delete(updateItem);
        Coupe deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
