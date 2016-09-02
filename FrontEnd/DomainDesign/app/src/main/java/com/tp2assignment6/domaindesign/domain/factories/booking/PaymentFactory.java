package com.tp2assignment6.domaindesign.domain.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class PaymentFactory {
    public static Payment getPayment(String type, String amount){
        return new Payment.Builder()
                .paymentType(type)
                .amount(amount)
                .build();
    }
}
