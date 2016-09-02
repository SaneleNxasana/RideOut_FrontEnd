package com.tp2assignment6.domaindesign.domain.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Sedan;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class SedanFactory {
    public static Sedan getSedan(String registration, String seats) {
        return new Sedan.Builder()
                .registrationNumber(registration)
                .numberSeats(seats)
                .build();
    }
}
