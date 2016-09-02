package com.tp2assignment6.domaindesign.services.employee;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.employee.Impl.SiteManagerServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class SiteManagerServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testSiteManagerService(){
        Intent intent = new Intent(getContext(), SiteManagerServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("7952130256LB", getContext());
        Assert.assertNull(getContext());
    }
}
