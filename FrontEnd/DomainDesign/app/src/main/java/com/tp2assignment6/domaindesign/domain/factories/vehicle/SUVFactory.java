package com.tp2assignment6.domaindesign.domain.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.SUV;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class SUVFactory {
    public static SUV getSUV(String registration, String seats) {
        return new SUV.Builder()
                .registrationNumber(registration)
                .numberSeats(seats)
                .build();
    }
}
