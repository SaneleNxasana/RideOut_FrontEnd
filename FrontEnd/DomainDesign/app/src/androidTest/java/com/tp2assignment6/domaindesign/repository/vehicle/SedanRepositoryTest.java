package com.tp2assignment6.domaindesign.repository.vehicle;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Sedan;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.SedanRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.SedanRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SedanRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SEDAN TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        SedanRepository repo = new SedanRepositoryImpl(this.getContext());

        //CREATE
        Sedan createEntity = new Sedan.Builder()
                .registrationNumber("CA 7913")
                .numberSeats("4")
                .build();
        Sedan insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Sedan> seden = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", seden.size() > 0);

        //READ ITEM
        Sedan item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Sedan updateItem = new Sedan.Builder()
                .copy(item)
                .registrationNumber("CA 9572")
                .build();
        repo.update(updateItem);
        Sedan newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "CA 9572", newItem.getRegistrationNumber());

        //DELETE ITEM
        repo.delete(updateItem);
        Sedan deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
