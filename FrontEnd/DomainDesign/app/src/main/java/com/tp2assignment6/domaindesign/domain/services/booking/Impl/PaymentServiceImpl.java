package com.tp2assignment6.domaindesign.domain.services.booking.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.PaymentRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.PaymentRepository;
import com.tp2assignment6.domaindesign.domain.services.booking.PaymentService;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS A BOUND SERVICE.
//This service is bound to another activity to function fully.

public class PaymentServiceImpl extends Service implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final IBinder localBinder = new PaymentServiceLocalBinder();
    private static PaymentServiceImpl service = null;

    public static PaymentServiceImpl getInstance(){
        if (service == null)
            service = new PaymentServiceImpl();
        return service;
    }

    public PaymentServiceImpl(){
        paymentRepository = new PaymentRepositoryImpl(App.getAppContext());
    }

    public class PaymentServiceLocalBinder extends Binder{
        public PaymentServiceImpl getService(){
            return PaymentServiceImpl.this;
        }
    }

    @Override
    public Payment readById(Long id) {
        return paymentRepository.readById(id);
    }

    @Override
    public Payment update(Payment entity) {
        return paymentRepository.update(entity);
    }

    @Override
    public Set<Payment> readAll() {
        return paymentRepository.readAll();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
}
