package com.tp2assignment6.domaindesign.domain.services.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */
public interface ReservationService {
    Reservation readById(Long id);
    Reservation update(Reservation entity);
    Set<Reservation> readAll();
}
