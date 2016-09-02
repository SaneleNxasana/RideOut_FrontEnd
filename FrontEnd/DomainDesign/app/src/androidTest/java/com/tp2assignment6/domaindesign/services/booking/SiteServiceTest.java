package com.tp2assignment6.domaindesign.services.booking;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Site;
import com.tp2assignment6.domaindesign.domain.factories.booking.SiteFactory;
import com.tp2assignment6.domaindesign.domain.services.booking.Impl.SiteServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/06/05.
 */
public class SiteServiceTest extends AndroidTestCase {
    private SiteServiceImpl siteService;
    private boolean isBound;
    private static final String TAG = "SITE TEST";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), SiteServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SiteServiceImpl.SiteServiceLocalBinder binder = (SiteServiceImpl.SiteServiceLocalBinder)service;
            siteService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void testCreateEntities() throws Exception{
        Site createEntity = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/125");
        Site newEntity = siteService.update(createEntity);
        Assert.assertNotNull(TAG + " CREATE", newEntity);
    }

    public void testCreateAndFindListOFEntities() throws Exception{
        Site createEntity1 = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/005");
        Site createEntity2 = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/006");
        Site createEntity3 = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/007");

        siteService.update(createEntity1);
        siteService.update(createEntity2);
        siteService.update(createEntity3);

        Set<Site> sites = siteService.readAll();
        Assert.assertTrue(sites.size() > 2);
    }
}
