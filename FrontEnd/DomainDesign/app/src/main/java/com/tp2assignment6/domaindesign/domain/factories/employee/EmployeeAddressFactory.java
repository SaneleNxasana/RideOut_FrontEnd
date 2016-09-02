package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class EmployeeAddressFactory {
    public static EmployeeAddress getEmployeeAddress(String desc, String code){
        return new EmployeeAddress.Builder()
                .description(desc)
                .postalCode(code)
                .build();
    }
}
