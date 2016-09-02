package com.tp2assignment6.domaindesign.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.System;
import com.tp2assignment6.domaindesign.domain.factories.booking.SystemFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SystemFactoryTest {
    @Test
    public void testCreate() throws Exception {
        System system = SystemFactory.getSystem("Jason Bourne", "03-June-2016, 4 Days");
        Assert.assertEquals("Jason Bourne", system.getUserDetails());
    }

    @Test
    public void testUpdate() throws Exception {
        System system = SystemFactory.getSystem("Jason Bourne", "03-June-2016, 4 Days");
        System newSystem = new System
                .Builder()
                .copy(system)
                .userDetails("James Bond")
                .build();
        Assert.assertEquals("James Bond", newSystem.getUserDetails());

    }
}
