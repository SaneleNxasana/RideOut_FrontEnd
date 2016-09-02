package com.tp2assignment6.domaindesign.domain.domain.employee;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class Chauffeur  implements Serializable {
    private Long idNumber;
    private String licenceNumber;

    public Chauffeur() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public static class Builder{
        private Long idNumber;
        private String licenceNumber;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder licenceNumber(String value){
            this.licenceNumber = value;
            return this;
        }

        public Builder copy(Chauffeur value){
            this.idNumber = value.idNumber;
            this.licenceNumber = value.licenceNumber;
            return this;
        }

        public Chauffeur build(){return new Chauffeur(this);}

    }

    public Chauffeur (Builder builder){
        this.idNumber = builder.idNumber;
        this.licenceNumber = builder.licenceNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Chauffeur that = (Chauffeur) obj;

        return idNumber.equals(that.idNumber);
    }

    @Override
    public int hashCode() {return idNumber.hashCode();}
}
