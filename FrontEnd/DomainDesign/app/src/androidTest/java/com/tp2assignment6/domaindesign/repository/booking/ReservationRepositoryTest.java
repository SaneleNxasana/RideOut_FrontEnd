package com.tp2assignment6.domaindesign.repository.booking;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.ReservationRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.ReservationRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class ReservationRepositoryTest extends AndroidTestCase {
    private static final String TAG = "RESERVATION TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        ReservationRepository repo = new ReservationRepositoryImpl(this.getContext());

        //CREATE
        Reservation createItem = new Reservation.Builder()
                .date("06 June 2016")
                .duration("7")
                .build();
        Reservation insertEntity = repo.create(createItem);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Reservation> reservations = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", reservations.size() > 0);

        //READ ITEM
        Reservation item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ENTITY
        Reservation updateItem = new Reservation.Builder()
                .copy(item)
                .duration("5")
                .build();
        repo.update(updateItem);
        Reservation newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "5", newItem.getDuration());

        //DELETE ITEM
        repo.delete(updateItem);
        Reservation deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
