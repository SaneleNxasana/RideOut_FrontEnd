package com.tp2assignment6.domaindesign.domain.domain.employee;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class EmployeeAddress implements Serializable{
    private Long idNumber;
    private String description;
    private String postalCode;

    public EmployeeAddress() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public static class Builder{
        private Long idNumber;
        private String description;
        private String postalCode;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder description(String value){
            this.description = value;
            return this;
        }
        public Builder postalCode(String value){
            this.postalCode = value;
            return this;
        }

        public Builder copy(EmployeeAddress value){
            this.idNumber = value.idNumber;
            this.description = value.description;
            this.postalCode = value.postalCode;
            return this;
        }

        public EmployeeAddress build(){return new EmployeeAddress(this);}
    }

    public EmployeeAddress(Builder builder){
        this.idNumber = builder.idNumber;
        this.description = builder.description;
        this.postalCode = builder.postalCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        EmployeeAddress that = (EmployeeAddress) obj;

        return idNumber.equals(that.idNumber);
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
