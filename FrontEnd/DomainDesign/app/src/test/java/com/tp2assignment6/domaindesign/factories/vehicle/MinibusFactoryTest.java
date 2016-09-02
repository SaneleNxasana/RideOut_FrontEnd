package com.tp2assignment6.domaindesign.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Minibus;
import com.tp2assignment6.domaindesign.domain.factories.vehicle.MinibusFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class MinibusFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Minibus minibus = MinibusFactory.getMinibus("CA 7946", "12");
        Assert.assertEquals("CA 7946", minibus.getRegistrationNumber());
    }

    @Test
    public void testUpdate() throws Exception {
        Minibus minibus = MinibusFactory.getMinibus("CA 7946", "12");
        Minibus newMinibus = new Minibus.Builder()
                .copy(minibus)
                .registrationNumber("CA 1346")
                .build();
        Assert.assertEquals("CA 1346", newMinibus.getRegistrationNumber());
    }
}
