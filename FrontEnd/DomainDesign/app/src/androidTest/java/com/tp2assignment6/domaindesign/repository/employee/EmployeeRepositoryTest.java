package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.Employee;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.EmployeeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class EmployeeRepositoryTest extends AndroidTestCase {
    private static final String TAG = "EMPLOYEE TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());

        //CREATE
        Employee createEntity = new Employee.Builder()
                .firstName("Bruce")
                .lastName("Wayne")
                .emailAddress("bruce@wayne.com")
                .build();
        Employee insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Employee> employees = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", employees.size() > 0);

        //READ ITEM
        Employee item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Employee updateItem = new Employee.Builder()
                .copy(item)
                .emailAddress("wayne@gotham.com")
                .build();
        repo.update(updateItem);
        Employee newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "wayne@gotham.com", newItem.getEmailAddress());

        //DELETE ITEM
        repo.delete(updateItem);
        Employee deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
