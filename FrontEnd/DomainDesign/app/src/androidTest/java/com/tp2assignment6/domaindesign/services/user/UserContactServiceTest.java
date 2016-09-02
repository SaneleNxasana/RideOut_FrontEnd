package com.tp2assignment6.domaindesign.services.user;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.services.user.Impl.UserContactServiceImpl;

import junit.framework.Assert;

/**
 * Created by NXA-C.unltd on 2016/05/11.
 */
public class UserContactServiceTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testUserContactService(){
        Intent intent = new Intent(getContext(), UserContactServiceImpl.class);
        super.mContext.startService(intent);
        Assert.assertEquals("083 459 1385", getContext());
        Assert.assertNotSame("060 493 0305", getContext());
    }
}
