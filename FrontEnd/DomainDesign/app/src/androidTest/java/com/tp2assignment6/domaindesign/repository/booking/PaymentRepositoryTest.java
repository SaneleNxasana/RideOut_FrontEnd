package com.tp2assignment6.domaindesign.repository.booking;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.PaymentRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.PaymentRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class PaymentRepositoryTest extends AndroidTestCase {
    private static final String TAG = "PAYMENT TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        PaymentRepository repo = new PaymentRepositoryImpl(this.getContext());

        //CREATE
        Payment createEntity = new Payment.Builder()
                .paymentType("Cash")
                .amount("R2000.00")
                .build();
        Payment insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Payment> payments = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", payments.size() > 0);

        //READ ITEM
        Payment item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Payment updateItem = new Payment.Builder()
                .copy(item)
                .amount("R2500.00")
                .build();
        repo.update(updateItem);
        Payment newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "R2500.00", newItem.getAmount());

        //DELETE ITEM
        repo.delete(updateItem);
        Payment deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
