package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.BusinessAdmin;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class BusinessAdminFactory {
    public static BusinessAdmin getBusinessAdmin(String pass){
        return new BusinessAdmin.Builder()
                .password(pass)
                .build();
    }
}
