package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class EmployeeContactFactory {
    public static EmployeeContact getEmployeeContact(String type, String value){
        return new EmployeeContact.Builder()
                .contactType(type)
                .contactValue(value)
                .build();
    }
}
