package com.tp2assignment6.domaindesign.services.employee;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.employee.Impl.ChauffeurServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class ChauffeurServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testChauffeurService(){
        Intent intent = new Intent(getContext(), ChauffeurServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("9713645202AC", getContext());
        Assert.assertNull(getContext());
    }
}
