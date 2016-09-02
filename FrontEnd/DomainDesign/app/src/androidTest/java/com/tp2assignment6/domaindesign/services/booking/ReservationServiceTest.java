package com.tp2assignment6.domaindesign.services.booking;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;
import com.tp2assignment6.domaindesign.domain.factories.booking.ReservationFactory;
import com.tp2assignment6.domaindesign.domain.services.booking.Impl.ReservationServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/06/05.
 */
public class ReservationServiceTest extends AndroidTestCase {
    private ReservationServiceImpl reservationService;
    private boolean isBound;
    private static final String TAG = "RESERVATION TEST";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), ReservationServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ReservationServiceImpl.ReservationServiceLocalBinder binder = (ReservationServiceImpl.ReservationServiceLocalBinder)service;
            reservationService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws  Exception{
        Reservation createEntity = ReservationFactory.getReservation("09 June 2016", "9 days");
        Reservation newEntity = reservationService.update(createEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception{
        Reservation createEntity1 = ReservationFactory.getReservation("17 June 2016", "3 days");
        Reservation createEntity2 = ReservationFactory.getReservation("02 July 2016", "7 days");
        Reservation createEntity3 = ReservationFactory.getReservation("09 July 2016", "11 days");

        reservationService.update(createEntity1);
        reservationService.update(createEntity2);
        reservationService.update(createEntity3);

        Set<Reservation> reservations = reservationService.readAll();
        Assert.assertTrue(reservations.size() > 2);
    }
}
