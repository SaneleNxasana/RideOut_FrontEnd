package com.tp2assignment6.domaindesign.domain.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;

import java.util.Date;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class ReservationFactory {
    public static Reservation getReservation(String date, String duration){
        return new Reservation.Builder()
                .date(date)
                .duration(duration)
                .build();
    }
}
