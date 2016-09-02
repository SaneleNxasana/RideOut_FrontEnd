package com.tp2assignment6.domaindesign.domain.domain.employee;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class EmployeeContact implements Serializable{
    private Long idNumber;
    private String contactType;
    private String contactValue;

    public EmployeeContact() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getContactType() {
        return contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public static class Builder{
        private Long idNumber;
        private String contactType;
        private String contactValue;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder contactType(String value){
            this.contactType = value;
            return this;
        }
        public Builder contactValue(String value){
            this.contactValue = value;
            return this;
        }

        public Builder copy(EmployeeContact value){
            this.idNumber = value.idNumber;
            this.contactType = value.contactType;
            this.contactValue = value.contactValue;
            return this;
        }

        public EmployeeContact build() {return new EmployeeContact(this);}
    }

    public EmployeeContact (Builder builder){
        this.idNumber = builder.idNumber;
        this.contactType = builder.contactType;
        this.contactValue = builder.contactValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        EmployeeContact that = (EmployeeContact) obj;

        return idNumber.equals(that.idNumber);
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
