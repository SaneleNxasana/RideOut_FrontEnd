package com.tp2assignment6.domaindesign.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Coupe;
import com.tp2assignment6.domaindesign.domain.factories.vehicle.CoupeFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class CoupeFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Coupe coupe = CoupeFactory.getCoupe("CA 8520", "2");
        Assert.assertEquals("CA 8520", coupe.getRegistrationNumber());
    }

    @Test
    public void testUpdate() throws Exception {
        Coupe coupe = CoupeFactory.getCoupe("CA 8520", "2");
        Coupe newCoupe = new Coupe.Builder()
                .copy(coupe)
                .registrationNumber("CA 2580")
                .build();
        Assert.assertEquals("CA 2580", newCoupe.getRegistrationNumber());
    }
}
