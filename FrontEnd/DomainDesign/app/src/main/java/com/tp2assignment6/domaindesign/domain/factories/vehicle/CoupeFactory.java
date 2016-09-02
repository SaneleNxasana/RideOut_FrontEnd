package com.tp2assignment6.domaindesign.domain.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Coupe;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class CoupeFactory {
    public static Coupe getCoupe(String registration, String seats){
        return new Coupe.Builder()
                .registrationNumber(registration)
                .numberSeats(seats)
                .build();
    }
}
