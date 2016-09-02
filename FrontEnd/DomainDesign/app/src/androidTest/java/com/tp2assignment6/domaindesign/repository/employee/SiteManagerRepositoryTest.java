package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.SiteManager;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.SiteManagerRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.employee.SiteManagerRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class SiteManagerRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SITE MANAGER TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        SiteManagerRepository repo = new SiteManagerRepositoryImpl(this.getContext());

        //CREATE
        SiteManager createEntity = new SiteManager.Builder()
                .password("$iteM@n47")
                .build();
        SiteManager insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<SiteManager> siteManagers = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", siteManagers.size() > 0);

        //READ ITEM
        SiteManager item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        SiteManager updateItem = new SiteManager.Builder()
                .copy(item)
                .password("siteP@s$")
                .build();
        repo.update(updateItem);
        SiteManager newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "siteP@s$", newItem.getPassword());

        //DELETE ITEM
        repo.delete(updateItem);
        SiteManager deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
