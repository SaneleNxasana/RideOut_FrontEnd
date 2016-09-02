package com.tp2assignment6.domaindesign.domain.factories.vehicle;

import com.tp2assignment6.domaindesign.domain.domain.vehicle.Vehicle;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class VehicleFactory {
    public static Vehicle getVehicle(String name, String model, String year){
        return new Vehicle.Builder()
                .vehicleName(name)
                .vehicleModel(model)
                .vehicleYear(year)
                .build();
    }

}
