package com.tp2assignment6.domaindesign.services.vehicle;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.MinibusServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class MinibusServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testMinibusService(){
        Intent intent = new Intent(getContext(), MinibusServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("CA 01235", getContext());
    }
}
