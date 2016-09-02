package com.tp2assignment6.domaindesign.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Sedan;
import com.tp2assignment6.domaindesign.domain.factories.vehicle.SedanFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SedanFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Sedan sedan = SedanFactory.getSedan("CA 2486", "4");
        Assert.assertEquals("CA 2486", sedan.getRegistrationNumber());
    }

    @Test
    public void testUpdate() throws Exception {
        Sedan sedan = SedanFactory.getSedan("CA 2486", "4");
        Sedan newSedan = new Sedan.Builder()
                .copy(sedan)
                .registrationNumber("CA 2468")
                .build();
        Assert.assertEquals("CA 2468", newSedan.getRegistrationNumber());
    }
}
