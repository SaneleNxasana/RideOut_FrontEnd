package com.tp2assignment6.domaindesign.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;
import com.tp2assignment6.domaindesign.domain.factories.booking.ReservationFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class ReservationFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Reservation reservation = ReservationFactory.getReservation("05-May-2016", "7");
        Assert.assertEquals("7", reservation.getDuration());
    }

    @Test
    public void testUpdate() throws Exception {
        Reservation reservation = ReservationFactory.getReservation("05-May-2016", "7");
        Reservation newreservation = new Reservation
                .Builder()
                .copy(reservation)
                .duration("9")
                .build();
        Assert.assertEquals("9", newreservation.getDuration());

    }
}
