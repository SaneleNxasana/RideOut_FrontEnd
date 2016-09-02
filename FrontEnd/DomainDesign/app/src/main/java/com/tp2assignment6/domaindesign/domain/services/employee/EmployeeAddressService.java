package com.tp2assignment6.domaindesign.domain.services.employee;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface EmployeeAddressService {
    void addEmployeeAddress(Context context, EmployeeAddress address);
    void updateEmployeeAddress(Context context, EmployeeAddress address);
}
