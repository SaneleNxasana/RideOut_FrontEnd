package com.tp2assignment6.domaindesign.domain.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.System;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SystemFactory {
    public static System getSystem(String user, String resDetails){
        return new System.Builder()
                .userDetails(user)
                .reservationDetails(resDetails)
                .build();
    }
}
