package com.tp2assignment6.domaindesign.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;
import com.tp2assignment6.domaindesign.domain.factories.booking.PaymentFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class PaymentFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Payment payment = PaymentFactory.getPayment("Credit", "R1500.00");
        Assert.assertEquals("Credit", payment.getPaymentType());
    }

    @Test
    public void testUpdate() throws Exception {
        Payment payment = PaymentFactory.getPayment("Credit", "R1500.00");
        Payment newPayment = new Payment
                .Builder()
                .copy(payment)
                .paymentType("Cash")
                .build();
        Assert.assertEquals("Cash", newPayment.getPaymentType());
        Assert.assertEquals("R1500.00", newPayment.getAmount());
    }
}
