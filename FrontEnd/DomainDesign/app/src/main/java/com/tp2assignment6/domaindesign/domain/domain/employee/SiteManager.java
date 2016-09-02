package com.tp2assignment6.domaindesign.domain.domain.employee;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class SiteManager implements Serializable {
    private Long idNumber;
    private String password;

    public SiteManager() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder{
        private Long idNumber;
        private String password;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder password(String value){
            this.password = value;
            return this;
        }

        public Builder copy(SiteManager value){
            this.idNumber = value.idNumber;
            this.password = value.password;
            return this;
        }

        public SiteManager build(){return new SiteManager(this);}

    }

    public SiteManager (Builder builder){
        this.idNumber = builder.idNumber;
        this.password = builder.password;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        SiteManager that = (SiteManager) obj;

        return idNumber.equals(that.idNumber);
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
