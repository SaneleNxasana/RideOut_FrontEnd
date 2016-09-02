package com.tp2assignment6.domaindesign.domain.services.booking.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.ReservationRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.ReservationRepository;
import com.tp2assignment6.domaindesign.domain.services.booking.ReservationService;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS A BOUND SERVICE.
//This service is bound to another activity to function fully.

public class ReservationServiceImpl extends Service implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final IBinder localBinder = new ReservationServiceLocalBinder();
    private static ReservationServiceImpl service = null;

    public static ReservationServiceImpl getInstance(){
        if (service == null)
            service = new ReservationServiceImpl();
        return service;
    }

    public ReservationServiceImpl(){
        reservationRepository = new ReservationRepositoryImpl(App.getAppContext());
    }

    public class ReservationServiceLocalBinder extends Binder{
        public ReservationServiceImpl getService(){
            return ReservationServiceImpl.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Reservation readById(Long id) {
        return reservationRepository.readById(id);
    }

    @Override
    public Reservation update(Reservation entity) {
        return reservationRepository.update(entity);
    }

    @Override
    public Set<Reservation> readAll() {
        return reservationRepository.readAll();
    }
}