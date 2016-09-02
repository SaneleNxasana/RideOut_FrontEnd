package com.tp2assignment6.domaindesign.services.employee;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.employee.Impl.EmployeeAddressServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class EmployeeAddressServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testEmployeeAddressService(){
        Intent intent = new Intent(getContext(), EmployeeAddressServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("127 Main Road, Woodstock, 7959", getContext());
        Assert.assertNotSame("29 Lower Main Road, Salt River, 7963", getContext());
    }
}
