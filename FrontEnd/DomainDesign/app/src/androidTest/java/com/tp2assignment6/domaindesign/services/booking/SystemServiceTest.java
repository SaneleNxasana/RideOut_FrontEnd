package com.tp2assignment6.domaindesign.services.booking;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.System;
import com.tp2assignment6.domaindesign.domain.factories.booking.SystemFactory;
import com.tp2assignment6.domaindesign.domain.services.booking.Impl.SystemServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/06/05.
 */
public class SystemServiceTest extends AndroidTestCase {
    private SystemServiceImpl systemService;
    private boolean isBound;
    private static final String TAG = "SYSTEM TEST";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), SystemServiceImpl.class);
        App.getAppContext().bindService(intent,  connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SystemServiceImpl.SystemServiceLocalBinder binder = (SystemServiceImpl.SystemServiceLocalBinder)service;
            systemService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception{
        System createEntity = SystemFactory.getSystem("830405 0000 000, Kevin Steez, kevin@steez.com", "07 June 2016, 4 days");
        System newEntity = systemService.update(createEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindListOfEntities() throws Exception{
        System createEntity1 = SystemFactory.getSystem("750606 0000 001, Clarke Lesner, clarke@lesner.com", "18 July 2016, 8 days");
        System createEntity2 = SystemFactory.getSystem("560118 0000 002, Steve Paulson, steve@paulson.com", "06 July 2016, 15 days");
        System createEntity3 = SystemFactory.getSystem("910505 0000 003, EZ Migee, ez@migee.com", "22 July 2016, 3 days");

        systemService.update(createEntity1);
        systemService.update(createEntity2);
        systemService.update(createEntity3);

        Set<System> systems = systemService.readAll();
        Assert.assertTrue(systems.size() > 2);
    }
}
