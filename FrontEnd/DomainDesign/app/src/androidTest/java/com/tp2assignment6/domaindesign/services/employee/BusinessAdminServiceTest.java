package com.tp2assignment6.domaindesign.services.employee;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.employee.Impl.BusinessAdminServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class BusinessAdminServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBusinessAdminService(){
        Intent intent = new Intent(getContext(), BusinessAdminServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("9856321485XY", getContext());
        Assert.assertNull(getContext());
    }
}
