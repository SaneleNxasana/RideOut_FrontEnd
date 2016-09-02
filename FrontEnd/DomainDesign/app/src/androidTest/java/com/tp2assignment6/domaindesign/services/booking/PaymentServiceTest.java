package com.tp2assignment6.domaindesign.services.booking;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;
import com.tp2assignment6.domaindesign.domain.factories.booking.PaymentFactory;
import com.tp2assignment6.domaindesign.domain.services.booking.Impl.PaymentServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/06/05.
 */
public class PaymentServiceTest extends AndroidTestCase {
    private PaymentServiceImpl paymentService;
    private boolean isBound;
    private static final String TAG = "PAYMENT TEST";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), PaymentServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PaymentServiceImpl.PaymentServiceLocalBinder binder = (PaymentServiceImpl.PaymentServiceLocalBinder) service;
            paymentService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception{
        Payment createEntity = PaymentFactory.getPayment("Cash", "2500.00");
        Payment newEntity = paymentService.update(createEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception{
        Payment createEntity1 = PaymentFactory.getPayment("Cheque", "7250.00");
        Payment createEntity2 = PaymentFactory.getPayment("Voucher", "3000.00");
        Payment createEntity3 = PaymentFactory.getPayment("Cash", "5500.00");

        paymentService.update(createEntity1);
        paymentService.update(createEntity2);
        paymentService.update(createEntity3);

        Set<Payment> payments = paymentService.readAll();
        Assert.assertTrue(payments.size() > 2);
    }
}
