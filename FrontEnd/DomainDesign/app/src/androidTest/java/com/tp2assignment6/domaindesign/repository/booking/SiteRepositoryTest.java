package com.tp2assignment6.domaindesign.repository.booking;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.booking.Site;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.SiteRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.SiteRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SiteRepositoryTest extends AndroidTestCase{
    private static final String TAG = "SITE TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        SiteRepository repo = new SiteRepositoryImpl(this.getContext());

        //CREATE
        Site createItem = new Site.Builder()
                .name("Car Hire")
                .url("www.carhire.com")
                .reservationUrl("www.carhire.com/reservation/001")
                .build();
        Site insertEntity = repo.create(createItem);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE");

        //READ ALL
        Set<Site> sites = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", sites.size() > 0);

        //READ ITEM
        Site item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Site updateItem = new Site.Builder()
                .copy(item)
                .reservationUrl("www.carhire.com/reservation/005")
                .build();
        repo.update(updateItem);
        Site newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "www.carhire.com/reservation/005", newItem.getReservationUrl());

        //DELETE ITEM
        repo.delete(updateItem);
        Site deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
