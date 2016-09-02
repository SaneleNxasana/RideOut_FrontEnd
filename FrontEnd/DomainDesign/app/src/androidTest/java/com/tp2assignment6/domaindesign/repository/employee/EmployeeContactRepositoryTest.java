package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeContactRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.EmployeeContactRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class EmployeeContactRepositoryTest extends AndroidTestCase {
    private static final String TAG = "EMPLOYEE CONTACT TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        EmployeeContactRepository repo = new EmployeeContactRepositoryImpl(this.getContext());

        //CREATE
        EmployeeContact createEntity = new EmployeeContact.Builder()
                .contactType("Cellphone")
                .contactValue("082 468 8462")
                .build();
        EmployeeContact insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<EmployeeContact> employeeContacts = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", employeeContacts.size() > 0);

        //READ ITEM
        EmployeeContact item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        EmployeeContact updateItem = new EmployeeContact.Builder()
                .copy(item)
                .contactValue("083 542 3010")
                .build();
        repo.update(updateItem);
        EmployeeContact newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "083 542 3010", newItem.getContactValue());

        //DELETE ITEM
        repo.delete(updateItem);
        EmployeeContact deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
