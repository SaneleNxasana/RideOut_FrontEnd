package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeAddressRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.EmployeeAddressRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class EmployeeAddressRepositoryTest extends AndroidTestCase {
    private static final String TAG = "EMPLOYEE ADDRESS TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        EmployeeAddressRepository repo = new EmployeeAddressRepositoryImpl(this.getContext());

        //CREATE
        EmployeeAddress createEntity = new EmployeeAddress.Builder()
                .description("100 Main Road, Cape Town")
                .postalCode("7992")
                .build();
        EmployeeAddress insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<EmployeeAddress> employeeAddresses = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", employeeAddresses.size() > 0);

        //READ ITEM
        EmployeeAddress item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        EmployeeAddress updateItem = new EmployeeAddress.Builder()
                .copy(item)
                .postalCode("8001")
                .build();
        repo.update(updateItem);
        EmployeeAddress newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "8001", newItem.getPostalCode());

        //DELETE ITEM
        repo.delete(updateItem);
        EmployeeAddress deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
