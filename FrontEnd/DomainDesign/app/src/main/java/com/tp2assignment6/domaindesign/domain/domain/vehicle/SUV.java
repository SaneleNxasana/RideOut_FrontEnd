package com.tp2assignment6.domaindesign.domain.domain.vehicle;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class SUV implements Serializable {
    private Long idNumber;
    private String registrationNumber;
    private String numberSeats;

    public SUV() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getNumberSeats() {
        return numberSeats;
    }

    public static class Builder{
        private Long idNumber;
        private String registrationNumber;
        private String numberSeats;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder registrationNumber(String value){
            this.registrationNumber = value;
            return this;
        }
        public Builder numberSeats(String value){
            this.numberSeats = value;
            return this;
        }

        public Builder copy(SUV value){
            this.idNumber = value.idNumber;
            this.registrationNumber = value.registrationNumber;
            this.numberSeats = value.numberSeats;
            return this;
        }

        public SUV build(){return new SUV(this);}
    }

    public SUV (Builder builder){
        this.idNumber = builder.idNumber;
        this.registrationNumber = builder.registrationNumber;
        this.numberSeats = builder.numberSeats;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SUV suv = (SUV) obj;
        return idNumber != null ? idNumber.equals(suv.idNumber) : suv.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
