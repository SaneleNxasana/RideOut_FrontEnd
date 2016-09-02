package com.tp2assignment6.domaindesign.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;
import com.tp2assignment6.domaindesign.domain.factories.employee.EmployeeContactFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class EmployeeContactFactoryTest {
    @Test
    public void testCreate() throws Exception {
        EmployeeContact employeeContact = EmployeeContactFactory.getEmployeeContact("Cellphone", "0821470258");
        Assert.assertEquals("Cellphone", employeeContact.getContactType());
    }

    @Test
    public void testUpdate() throws Exception {
        EmployeeContact employeeContact = EmployeeContactFactory.getEmployeeContact("Cellphone", "0821470258");
        EmployeeContact newEmployeeContact = new EmployeeContact
                .Builder()
                .copy(employeeContact)
                .contactType("Email")
                .contactValue("emp@work.com")
                .build();
        Assert.assertEquals("Email", newEmployeeContact.getContactType());
//        Assert.assertEquals("emp@work", newEmployeeContact.getContactValue());
    }
}