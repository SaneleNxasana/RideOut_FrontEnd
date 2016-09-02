package com.tp2assignment6.domaindesign.repository.vehicle;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.SUV;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.SUVRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.SUVRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SUVRepositoryTest extends AndroidTestCase{
    private static final String TAG = "SUV TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        SUVRepository repo = new SUVRepositoryImpl(this.getContext());

        //CREATE
        SUV createEntity = new SUV.Builder()
                .registrationNumber("CA 8520")
                .numberSeats("5")
                .build();
        SUV insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<SUV> suvs = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", suvs.size() > 0);

        //READ ITEM
        SUV item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        SUV updateItem = new SUV.Builder()
                .copy(item)
                .registrationNumber("CA 4835")
                .build();
        repo.update(updateItem);
        SUV newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "CA 4835", newItem.getRegistrationNumber());

        //DELETE ITEM
        repo.delete(updateItem);
        SUV deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
