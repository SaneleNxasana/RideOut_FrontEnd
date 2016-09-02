package com.tp2assignment6.domaindesign.services.employee;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.employee.Impl.EmployeeContactServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/12.
 */
public class EmployeeContactServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testEmployeeContactService(){
        Intent intent = new Intent(getContext(), EmployeeContactServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("061 761 0357", getContext());
        Assert.assertNotSame("078 523 4930", getContext());
    }
}
