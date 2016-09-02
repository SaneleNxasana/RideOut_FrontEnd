package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.Employee;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class EmployeeFactory {
    public static Employee getEmployee(String name, String surname, String email){
        return new Employee.Builder()
                .firstName(name)
                .lastName(surname)
                .emailAddress(email)
                .build();
    }
}
