package com.tp2assignment6.domaindesign.domain.domain.vehicle;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class Vehicle implements Serializable {
    private Long idNumber;
    private String vehicleName;
    private String vehicleModel;
    private String vehicleYear;

    public Vehicle() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public static class Builder{
        private Long idNumber;
        private String vehicleName;
        private String vehicleModel;
        private  String vehicleYear;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }

        public Builder vehicleName(String value){
            this.vehicleName = value;
            return this;
        }

        public Builder vehicleModel(String value){
            this.vehicleModel = value;
            return this;
        }

        public Builder vehicleYear(String value){
            this.vehicleYear = value;
            return this;
        }

        public Builder copy(Vehicle value){
            this.idNumber = value.idNumber;
            this.vehicleName = value.vehicleName;
            this.vehicleModel = value.vehicleModel;
            this.vehicleYear = value.vehicleYear;
            return this;
        }

        public Vehicle build(){return new Vehicle(this);}
    }

    public Vehicle(Builder builder){
        this.idNumber = builder.idNumber;
        this.vehicleName = builder.vehicleName;
        this.vehicleModel = builder.vehicleModel;
        this.vehicleYear = builder.vehicleYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return idNumber != null ? idNumber.equals(vehicle.idNumber) : vehicle.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
