package com.tp2assignment6.domaindesign.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;
import com.tp2assignment6.domaindesign.domain.factories.employee.EmployeeAddressFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class EmployeeAddressFactoryTest {
    @Test
    public void testCreate() throws Exception {
        EmployeeAddress employeeAddress = EmployeeAddressFactory.getEmployeeAddress("5 Dorset Street", "8001");
        Assert.assertEquals("8001", employeeAddress.getPostalCode());
    }

    @Test
    public void testUpdate() throws Exception {
        EmployeeAddress employeeAddress = EmployeeAddressFactory.getEmployeeAddress("5 Dorset Street", "8001");
        EmployeeAddress newEmployeeAddress = new EmployeeAddress
                .Builder()
                .copy(employeeAddress)
                .postalCode("7992")
                .build();
        Assert.assertEquals("7992", newEmployeeAddress.getPostalCode());
    }
}
