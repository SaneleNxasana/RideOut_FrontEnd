package com.tp2assignment6.domaindesign.domain.domain.booking;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class System implements Serializable{
    private Long idNumber;
    private String userDetails;
    private String reservationDetails;

    public System() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public String getReservationDetails() {
        return reservationDetails;
    }

    public static class Builder{
        private Long idNumber;
        private String userDetails;
        private String reservationDetails;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder userDetails(String value){
            this.userDetails = value;
            return this;
        }
        public Builder reservationDetails(String value){
            this.reservationDetails = value;
            return this;
        }

        public Builder copy(System value){
            this.idNumber = value.idNumber;
            this.userDetails = value.userDetails;
            this.reservationDetails = value.reservationDetails;
            return this;
        }

        public System build() {return new System(this);}
    }

    public System(Builder builder){
        this.idNumber = builder.idNumber;
        this.userDetails = builder.userDetails;
        this.reservationDetails = builder.reservationDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        System that = (System) obj;

        return idNumber != null ? idNumber.equals(that.idNumber) : that.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber != null ? idNumber.hashCode() : 0;}
}
