package com.tp2assignment6.domaindesign.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Site;
import com.tp2assignment6.domaindesign.domain.factories.booking.SiteFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SiteFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Site site = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/001");
        Assert.assertEquals("Car Hire", site.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        Site site = SiteFactory.getSite("Car Hire", "www.carhire.com", "www.carhire.com/reservation/001");
        Site newSite = new Site
                .Builder()
                .copy(site)
                .reservationUrl("www.carhire.com/reservation/002")
                .build();
        Assert.assertEquals("www.carhire.com/reservation/002", newSite.getReservationUrl());
    }
}
