package com.tp2assignment6.domaindesign.services.vehicle;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.CoupeServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/10.
 */
public class CoupeServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCoupeService(){
        Intent intent = new Intent(getContext(), CoupeServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("CA 78952", getContext());
    }
}