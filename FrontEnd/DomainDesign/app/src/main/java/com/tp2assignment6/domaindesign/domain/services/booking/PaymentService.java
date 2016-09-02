package com.tp2assignment6.domaindesign.domain.services.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */
public interface PaymentService {
    Payment readById(Long id);
    Payment update(Payment entity);
    Set<Payment> readAll();
}
