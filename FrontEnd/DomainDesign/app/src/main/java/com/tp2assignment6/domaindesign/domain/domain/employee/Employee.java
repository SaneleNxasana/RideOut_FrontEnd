package com.tp2assignment6.domaindesign.domain.domain.employee;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class Employee implements Serializable {
    private Long idNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Employee() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static class Builder{
        private Long idNumber;
        private String firstName;
        private String lastName;
        private String emailAddress;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder firstName(String value){
            this.firstName = value;
            return this;
        }
        public Builder lastName(String value){
            this.lastName = value;
            return this;
        }
        public Builder emailAddress(String value){
            this.emailAddress = value;
            return this;
        }

        public Builder copy(Employee value){
            this.idNumber = value.idNumber;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.emailAddress = value.emailAddress;
            return this;
        }

        public Employee build(){return new Employee(this);}
    }

    public Employee (Builder builder){
        this.idNumber = builder.idNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;

        return idNumber != null ? idNumber.equals(employee.idNumber) : employee.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}