package com.tp2assignment6.domaindesign.domain.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class User implements Serializable {
    private Long idNumber;
    private String userID;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public User(Builder builder){
        this.idNumber = builder.idNumber;
        this.userID = builder.userID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    public static class Builder{
        private Long idNumber;
        private String userID;
        private String firstName;
        private String lastName;
        private String email;

        public Builder idNumber(Long value) {
            this.idNumber = value;
            return this;
        }

        public Builder userID(String value){
            this.userID = value;
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

        public Builder email(String value){
            this.email = value;
            return this;
        }

        public Builder copy(User value){
            this.idNumber = value.idNumber;
            this.userID = value.userID;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.email = value.email;
            return this;
        }

        public User build(){return new User(this);}
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return idNumber != null ? idNumber.equals(user.idNumber) : user.idNumber == null;
    }

    public int hashCode() {
        return idNumber != null ? idNumber.hashCode() : 0;
    }
}
