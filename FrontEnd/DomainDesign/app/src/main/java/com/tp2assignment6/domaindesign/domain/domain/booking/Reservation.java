package com.tp2assignment6.domaindesign.domain.domain.booking;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class Reservation implements Serializable {
    private Long idNumber;
    private String date;
    private String duration;

    public Reservation() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public static class Builder{
        private Long idNumber;
        private String date;
        private String duration;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder date(String value){
            this.date = value;
            return this;
        }
        public Builder duration(String value){
            this.duration = value;
            return this;
        }

        public Builder copy(Reservation value){
            this.idNumber = value.idNumber;
            this.date = value.date;
            this.duration = value.duration;
            return this;
        }

        public Reservation build(){return new Reservation(this);}
    }

    public Reservation(Builder builder){
        this.idNumber = builder.idNumber;
        this.date = builder.date;
        this.duration = builder.duration;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Reservation that = (Reservation) obj;

        return idNumber != null ? idNumber.equals(that.idNumber) : that.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber != null ? idNumber.hashCode() : 0;}
}
