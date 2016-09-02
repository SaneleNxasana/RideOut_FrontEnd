package com.tp2assignment6.domaindesign.repository.vehicle;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Minibus;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.MinibusRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.MinibusRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class MinibusRepositoryTest extends AndroidTestCase {
    private static final String TAG = "MINIBUS TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        MinibusRepository repo = new MinibusRepositoryImpl(this.getContext());

        //CREATE
        Minibus createEntity = new Minibus.Builder()
                .registrationNumber("CA 1793")
                .numberSeats("12")
                .build();
        Minibus insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Minibus> minibuses = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", minibuses.size() > 0);

        //READ ITEM
        Minibus item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Minibus updateItem = new Minibus.Builder()
                .copy(item)
                .registrationNumber("CA 2023")
                .build();
        repo.update(updateItem);
        Minibus newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "CA 2023", newItem.getRegistrationNumber());

        //DELETE ITEM
        repo.delete(updateItem);
        Minibus deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
