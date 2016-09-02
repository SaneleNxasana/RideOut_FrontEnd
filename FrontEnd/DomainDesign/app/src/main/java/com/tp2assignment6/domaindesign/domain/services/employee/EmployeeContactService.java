package com.tp2assignment6.domaindesign.domain.services.employee;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface EmployeeContactService {
    void addEmployeeContact(Context context, EmployeeContact contact);
    void updateEmployeeContact(Context context, EmployeeContact contact);
}
