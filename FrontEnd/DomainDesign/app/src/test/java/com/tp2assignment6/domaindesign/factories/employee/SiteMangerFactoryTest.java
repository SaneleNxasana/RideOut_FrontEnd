package com.tp2assignment6.domaindesign.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.SiteManager;
import com.tp2assignment6.domaindesign.domain.factories.employee.SiteManagerFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SiteMangerFactoryTest {
    @Test
    public void testCreate() throws Exception {
        SiteManager siteManager = SiteManagerFactory.getSiteManger("siteMan");
        Assert.assertEquals("siteMan", siteManager.getPassword());
    }

    @Test
    public void testUpdate() throws Exception {
        SiteManager siteManager = SiteManagerFactory.getSiteManger("siteMan");
        SiteManager newSiteManger = new SiteManager
                .Builder()
                .copy(siteManager)
                .password("siteMan002")
                .build();
        Assert.assertEquals("siteMan002", newSiteManger.getPassword());

    }
}
