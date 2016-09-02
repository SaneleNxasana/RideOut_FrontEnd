package com.tp2assignment6.domaindesign.services.vehicle;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.SedanServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class SedanServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testSedanService(){
        Intent intent = new Intent(getContext(), SedanServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("CA 15935", getContext());
    }
}
