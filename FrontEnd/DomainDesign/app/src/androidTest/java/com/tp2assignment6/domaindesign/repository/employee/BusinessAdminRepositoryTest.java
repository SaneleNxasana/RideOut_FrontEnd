package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.BusinessAdmin;
import com.tp2assignment6.domaindesign.domain.repository.employee.BusinessAdminRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.BusinessAdminRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class BusinessAdminRepositoryTest extends AndroidTestCase {
    private static final String TAG = "BUSINESS ADMIN TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        BusinessAdminRepository repo = new BusinessAdminRepositoryImpl(this.getContext());

        //CREATE
        BusinessAdmin createEntity = new BusinessAdmin.Builder()
                .password("Bus@admin123")
                .build();
        BusinessAdmin insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<BusinessAdmin> businessAdmin = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", businessAdmin.size() > 0);

        //READ ITEM
        BusinessAdmin item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        BusinessAdmin updateItem = new BusinessAdmin.Builder()
                .copy(item)
                .password("bAdmin#2258")
                .build();
        repo.update(updateItem);
        BusinessAdmin newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "R2500.00", newItem.getPassword());

        //DELETE ITEM
        repo.delete(updateItem);
        BusinessAdmin deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
