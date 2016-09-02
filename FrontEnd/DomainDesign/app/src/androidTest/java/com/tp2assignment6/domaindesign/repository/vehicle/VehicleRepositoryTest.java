package com.tp2assignment6.domaindesign.repository.vehicle;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Vehicle;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.VehicleRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.VehicleRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/24.
 */
public class VehicleRepositoryTest extends AndroidTestCase {
    private static final String TAG = "VEHICLE TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        VehicleRepository repo = new VehicleRepositoryImpl(this.getContext());

        //CREATE
        Vehicle createItem = new Vehicle.Builder()
                .vehicleName("BMW")
                .vehicleModel("530i")
                .vehicleYear("2013")
                .build();
        Vehicle insertEntity = repo.create(createItem);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Vehicle> vehicles = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", vehicles.size() > 0);

        //READ ITEM
        Vehicle item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Vehicle updateItem = new Vehicle.Builder()
                .copy(item)
                .vehicleModel("335i")
                .build();
        repo.update(updateItem);
        Vehicle newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "335i", newItem.getVehicleModel());

        //DELETE
        repo.delete(updateItem);
        Vehicle deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
