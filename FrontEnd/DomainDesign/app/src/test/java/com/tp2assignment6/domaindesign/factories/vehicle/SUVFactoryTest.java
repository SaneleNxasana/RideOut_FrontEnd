package com.tp2assignment6.domaindesign.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.SUV;
import com.tp2assignment6.domaindesign.domain.factories.vehicle.SUVFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class SUVFactoryTest {
    @Test
    public void testCreate() throws Exception {
        SUV suv = SUVFactory.getSUV("CA 1793", "5");
        Assert.assertEquals("CA 1793", suv.getRegistrationNumber());
    }

    @Test
    public void testUpdate() throws Exception {
        SUV suv = SUVFactory.getSUV("CA 1793", "5");
        SUV newSUV = new SUV.Builder()
                .copy(suv)
                .registrationNumber("CA 1795")
                .build();
        Assert.assertEquals("CA 1795", newSUV.getRegistrationNumber());
    }
}
