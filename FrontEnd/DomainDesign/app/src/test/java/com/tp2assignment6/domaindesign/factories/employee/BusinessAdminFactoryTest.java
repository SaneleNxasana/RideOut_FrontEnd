package com.tp2assignment6.domaindesign.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.BusinessAdmin;
import com.tp2assignment6.domaindesign.domain.factories.employee.BusinessAdminFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class BusinessAdminFactoryTest {
    @Test
    public void testCreate() throws Exception {
        BusinessAdmin businessAdmin = BusinessAdminFactory.getBusinessAdmin("admin");
        Assert.assertEquals("admin", businessAdmin.getPassword());
    }

    @Test
    public void testUpdate() throws Exception {
        BusinessAdmin businessAdmin = BusinessAdminFactory.getBusinessAdmin("admin");
        BusinessAdmin newBusinessAdmin = new BusinessAdmin
                .Builder()
                .copy(businessAdmin)
                .password("admin2")
                .build();
        Assert.assertEquals("admin2", newBusinessAdmin.getPassword());
    }
}
