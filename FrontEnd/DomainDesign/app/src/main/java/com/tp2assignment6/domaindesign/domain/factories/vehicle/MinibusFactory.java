package com.tp2assignment6.domaindesign.domain.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Minibus;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class MinibusFactory {
    public static Minibus getMinibus(String registration, String seats) {
        return new Minibus.Builder()
                .registrationNumber(registration)
                .numberSeats(seats)
                .build();
    }
}
